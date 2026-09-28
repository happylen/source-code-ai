package com.kmbeast.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kmbeast.context.LocalThreadHolder;
import com.kmbeast.mapper.BooklistMapper;
import com.kmbeast.pojo.dto.BooklistQueryDTO;
import com.kmbeast.pojo.dto.BooklistSaveDTO;
import com.kmbeast.pojo.dto.UserActionQueryDTO;
import com.kmbeast.pojo.entity.BookCategory;
import com.kmbeast.pojo.entity.Booklist;
import com.kmbeast.pojo.entity.UserAction;
import com.kmbeast.pojo.enums.UserActionTypeEnum;
import com.kmbeast.pojo.vo.BooklistDetailVO;
import com.kmbeast.pojo.vo.BooklistLendTopVO;
import com.kmbeast.pojo.vo.BooklistVO;
import com.kmbeast.pojo.vo.ScoreVO;
import com.kmbeast.service.BookCategoryService;
import com.kmbeast.service.BooklistService;
import com.kmbeast.service.LoansService;
import com.kmbeast.service.UserActionService;
import com.kmbeast.utils.AssertUtils;
import com.kmbeast.utils.RoleValidUtils;
import com.kmbeast.utils.UserBasedCFUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 书目业务逻辑接口实现类
 */
@Service
public class BooklistServiceImpl extends ServiceImpl<BooklistMapper, Booklist> implements BooklistService {

    @Autowired
    private BookCategoryService bookCategoryService;
    @Autowired
    private UserActionService userActionService;
    @Autowired
    private LoansService loansService;

    /**
     * 参数校验
     *
     * @param booklist 书目实体类
     */
    private void validParams(Booklist booklist) {
        AssertUtils.notNull(booklist, "参数项不为空");
        AssertUtils.hasText(booklist.getName(), "书目名不为空");
        AssertUtils.hasText(booklist.getCover(), "封面必须上传");
        AssertUtils.hasText(booklist.getAuthor(), "作者不能为空");
        AssertUtils.hasText(booklist.getPublisher(), "请填写出版商");
        AssertUtils.hasText(booklist.getDetail(), "请填写介绍");
        AssertUtils.notNull(booklist.getPublishCount(), "请填写版次");
        AssertUtils.hasText(booklist.getPublishYear(), "请填写出版年份");
        AssertUtils.hasText(booklist.getIsbn(), "isbn（国际标准书号）不能为空");
    }

    /**
     * 书目新增
     *
     * @param booklistSaveDTO 书目实体类
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addBooklist(BooklistSaveDTO booklistSaveDTO) {
        // 参数校验
        validParams(booklistSaveDTO);
        RoleValidUtils.requestedAdmin("无操作权限");
        // 长度限制
        limitStrLength(booklistSaveDTO);
        booklistSaveDTO.setCreateTime(LocalDateTime.now()); // 设置书目的新增时间
        // 书目新增
        save(booklistSaveDTO);
        // 完成书目与图书类别的绑定
        batchSaveBookCategory(booklistSaveDTO);
    }

    /**
     * 长度限制
     *
     * @param booklist 实体
     */
    private void limitStrLength(Booklist booklist) {
        AssertUtils.isTrue(booklist.getName().length() < 50, "书目名称长度上限50字");
        AssertUtils.isTrue(booklist.getDetail().length() < 200, "书目介绍长度上限200字");
        AssertUtils.isTrue(booklist.getPublisher().length() < 50, "书目出版社名称长度上限50字");
    }

    /**
     * 书目修改
     *
     * @param booklistSaveDTO 书目实体类
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBooklist(BooklistSaveDTO booklistSaveDTO) {
        AssertUtils.notNull(booklistSaveDTO, "书目不为空");
        AssertUtils.notNull(booklistSaveDTO.getId(), "书目ID不为空");
        RoleValidUtils.requestedAdmin("无操作权限");
        // 书目修改
        updateById(booklistSaveDTO);
        // 先把书目之前绑定的全部删掉，再新增
        bookCategoryService.remove(
                new LambdaQueryWrapper<BookCategory>()
                        .eq(BookCategory::getBooklistId, booklistSaveDTO.getId())
        );
        batchSaveBookCategory(booklistSaveDTO);
    }

    private void batchSaveBookCategory(BooklistSaveDTO booklistSaveDTO) {
        // 完成书目与图书类别的绑定 - 新增
        AssertUtils.notEmpty(booklistSaveDTO.getCategoryIds(), "图书类别需选中");
        // 获取书目新增之后的数据库自增ID
        Integer booklistId = booklistSaveDTO.getId();
        List<BookCategory> bookCategoryList = booklistSaveDTO.getCategoryIds().stream()
                .map(categoryId ->
                        new BookCategory(null, booklistId, categoryId))
                .toList();
        // 批量新增书目与图书类别的绑定关系
        bookCategoryService.saveBatch(bookCategoryList);
    }

    /**
     * 删除书目
     *
     * @param id 主键ID
     */
    @Override
    public void delBooklist(Integer id) {
        AssertUtils.notNull(id, "ID不能为空");
        RoleValidUtils.requestedAdmin("无操作权限");
        removeById(id);
    }

    /**
     * 查询书目
     *
     * @param booklistQueryDTO 查询条件类
     * @return List<BooklistVO> 书目VO集合
     */
    @Override
    public List<BooklistVO> queryPage(BooklistQueryDTO booklistQueryDTO) {
        return this.baseMapper.queryPage(booklistQueryDTO);
    }

    /**
     * 查询符合条件的总条数
     *
     * @param booklistQueryDTO 查询条件类
     * @return Integer
     */
    @Override
    public Integer queryPageCount(BooklistQueryDTO booklistQueryDTO) {
        return this.baseMapper.queryPageCount(booklistQueryDTO);
    }

    /**
     * 查询书目的详情
     *
     * @param id 主键ID
     * @return BooklistDetailVO
     */
    @Override
    public BooklistDetailVO detail(Integer id) {
        AssertUtils.notNull(id, "ID不为空");
        return this.baseMapper.detail(id);
    }

    /**
     * 查询用户收藏的书目信息
     *
     * @param booklistQueryDTO 查询条件类
     * @return List<BooklistVO>
     */
    @Override
    public List<BooklistVO> collection(BooklistQueryDTO booklistQueryDTO) {
        // 查询用户收藏的书目列表
        UserActionQueryDTO userActionQueryDTO = new UserActionQueryDTO();
        userActionQueryDTO.setUserId(LocalThreadHolder.getUserId()); // 设置上当前操作者用户ID
        userActionQueryDTO.setType(UserActionTypeEnum.STATUS_1.getStatus()); // 设置行为类型 - 收藏
        List<UserAction> userActions = userActionService.queryPage(userActionQueryDTO);
        if (CollectionUtils.isEmpty(userActions)) {
            return new ArrayList<>();
        }
        List<Integer> booklistIds = userActions.stream()
                .map(UserAction::getBooklistId)
                .toList();
        booklistQueryDTO.setBooklistIds(booklistIds);
        return this.baseMapper.queryPage(booklistQueryDTO);
    }

    @Override
    public List<LocalDateTime> launchInfo() {
        return this.baseMapper.launchInfo();
    }

    /**
     * 查询借阅最多的书目
     *
     * @param count 查询条数
     * @return List<BooklistVO>
     */
    @Override
    public List<BooklistVO> lendTop(Integer count) {
        // 查询借阅最多的书目数据
        List<BooklistLendTopVO> booklistLendTopVOS = loansService.queryBooklistLendTop(count);
        if (CollectionUtils.isEmpty(booklistLendTopVOS)) {
            return new ArrayList<>();
        }
        List<Integer> booklistIds = booklistLendTopVOS.stream()
                .map(BooklistLendTopVO::getBooklistId)
                .toList();
        BooklistQueryDTO booklistQueryDTO = new BooklistQueryDTO();
        booklistQueryDTO.setBooklistIds(booklistIds);
        return this.baseMapper.queryPage(
                booklistQueryDTO
        );
    }

    @Override
    public List<BooklistLendTopVO> booklistLendTopVOS(Integer count) {
        return loansService.queryBooklistLendTop(count);
    }

    /**
     * 查询向用户推荐的图书 - 协同过滤算法推荐
     *
     * @param count 推荐本数
     * @return List<BooklistVO>
     */
    @Override
    public List<BooklistVO> recommend(Integer count) {
        // 获取书目ID列表
        List<Integer> booklistIds = this.baseMapper.getIds();
        if (booklistIds.isEmpty()) {
            return new ArrayList<>();
        }
        // 获取用户对于物品的评分
        List<ScoreVO> scores = userActionService.scores();
        List<UserBasedCFUtil.Score> scoreList = scores.stream().map(
                scoreVO -> new UserBasedCFUtil.Score(
                        scoreVO.getUserId(),
                        scoreVO.getBooklistId(),
                        scoreVO.getScore()
                )
        ).toList();
        Map<Integer, Map<Integer, Double>> metaMatrix = UserBasedCFUtil.buildUserItemMatrix(
                booklistIds,
                scoreList
        );
        UserBasedCFUtil cfUtil = new UserBasedCFUtil(metaMatrix);
        // 计算回来的物品ID列表
        List<Integer> itemIds = cfUtil.recommendItems(LocalThreadHolder.getUserId(), count);
        System.out.println("为用户ID为【"+LocalThreadHolder.getUserId()+"】推荐的图书ID列表："+itemIds);
        BooklistQueryDTO booklistQueryDTO = new BooklistQueryDTO();
        // 冷启动阶段 - 算不出来东西，因为用户压根没有行为数据，何谈兴趣？推荐借的比较多的书即可
        if (CollectionUtils.isEmpty(itemIds)) {
            List<BooklistLendTopVO> booklistLendTopVOS = loansService.queryBooklistLendTop(count);
            if (CollectionUtils.isEmpty(booklistLendTopVOS)) {
                return new ArrayList<>();
            }
            List<Integer> lendTopBooklistIds = booklistLendTopVOS.stream().map(
                    BooklistLendTopVO::getBooklistId
            ).toList();
            booklistQueryDTO.setBooklistIds(lendTopBooklistIds);
        } else {
            booklistQueryDTO.setBooklistIds(itemIds);
        }
        return this.baseMapper.queryPage(booklistQueryDTO);
    }


}
