/*
 Navicat Premium Dump SQL

 Source Server         : local_dev_db
 Source Server Type    : MySQL
 Source Server Version : 50743 (5.7.43-log)
 Source Host           : localhost:3306
 Source Schema         : booksystem

 Target Server Type    : MySQL
 Target Server Version : 50743 (5.7.43-log)
 File Encoding         : 65001

 Date: 08/12/2025 23:30:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book_category
-- ----------------------------
DROP TABLE IF EXISTS `book_category`;
CREATE TABLE `book_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '图书类别中间表主键ID，自增',
  `booklist_id` int(11) NULL DEFAULT NULL COMMENT '书目ID，外键，关联的是书目信息表',
  `category_id` int(11) NULL DEFAULT NULL COMMENT '图书类别ID，外键，关联的是图书类别表',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_category_id`(`category_id`) USING BTREE COMMENT '索引：图书类别ID',
  INDEX `idx_booklist_id`(`booklist_id`) USING BTREE COMMENT '索引：书目ID'
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '图书类别中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_category
-- ----------------------------
INSERT INTO `book_category` VALUES (13, 11, 9);
INSERT INTO `book_category` VALUES (14, 11, 14);
INSERT INTO `book_category` VALUES (15, 11, 15);
INSERT INTO `book_category` VALUES (29, 3, 14);
INSERT INTO `book_category` VALUES (30, 3, 9);
INSERT INTO `book_category` VALUES (31, 3, 34);
INSERT INTO `book_category` VALUES (34, 6, 8);
INSERT INTO `book_category` VALUES (35, 6, 122);
INSERT INTO `book_category` VALUES (36, 6, 123);
INSERT INTO `book_category` VALUES (37, 4, 8);
INSERT INTO `book_category` VALUES (38, 4, 124);
INSERT INTO `book_category` VALUES (42, 12, 128);
INSERT INTO `book_category` VALUES (43, 12, 131);
INSERT INTO `book_category` VALUES (44, 12, 129);
INSERT INTO `book_category` VALUES (45, 13, 131);
INSERT INTO `book_category` VALUES (46, 13, 129);
INSERT INTO `book_category` VALUES (47, 13, 128);
INSERT INTO `book_category` VALUES (48, 14, 128);
INSERT INTO `book_category` VALUES (49, 14, 133);
INSERT INTO `book_category` VALUES (50, 14, 129);
INSERT INTO `book_category` VALUES (51, 15, 128);
INSERT INTO `book_category` VALUES (52, 15, 132);
INSERT INTO `book_category` VALUES (53, 15, 129);
INSERT INTO `book_category` VALUES (54, 16, 128);
INSERT INTO `book_category` VALUES (55, 16, 129);
INSERT INTO `book_category` VALUES (56, 16, 132);

-- ----------------------------
-- Table structure for book_item
-- ----------------------------
DROP TABLE IF EXISTS `book_item`;
CREATE TABLE `book_item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '馆藏书目主键ID，自增',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '条形码',
  `booklist_id` int(11) NULL DEFAULT NULL COMMENT '书目ID，外键，关联的是书目表',
  `location` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '馆藏位置',
  `record` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  `buy_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '采购价',
  `buy_date` date NULL DEFAULT NULL COMMENT '采购日期',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态（1-在馆；2-借出；3-损毁；4-销毁）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_booklist_id`(`booklist_id`) USING BTREE COMMENT '索引：书目ID'
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '馆藏书目（物理本）信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_item
-- ----------------------------
INSERT INTO `book_item` VALUES (1, 'TYTYUY6565', 3, '2楼2单元3号架', '轻微损毁，不影响观看', 10.90, '2025-12-02', 2);
INSERT INTO `book_item` VALUES (3, 'F6A77E1E-224E-4B45-B9F7-02ACD5CE81F6', 3, '3楼1单元12书架2排', '准新', 12.90, '2025-12-02', 2);
INSERT INTO `book_item` VALUES (4, '89AEB364-9A64-40A4-B3B6-D3198500ADDF', 3, '3楼1单元12书架3排', '略有磨损', 12.80, '2025-11-08', 2);
INSERT INTO `book_item` VALUES (6, 'F99657C7-2A54-41D3-8A69-2169538FBCEB', 2, '2楼1单元1货架', '全新系列', 12.20, '2025-12-03', 2);
INSERT INTO `book_item` VALUES (7, '3B49A717-ADD3-4B4C-9861-7D1DDDBFF47F', 4, '一楼2单元1号书架', '全新', 23.30, '2025-12-01', 2);
INSERT INTO `book_item` VALUES (8, '2F6742B3-60D0-4EDE-8197-CCA4F3A277F4', 16, '一楼1号架1区', '8成新', 69.90, '2025-10-05', 1);
INSERT INTO `book_item` VALUES (9, '2AD160F8-F552-4523-827D-F78BD039FC65', 16, '一楼1号架1区', '全新', 69.90, '2025-12-06', 2);
INSERT INTO `book_item` VALUES (10, 'C05E5069-AA28-4A6C-92E0-0050E8962991', 16, '一楼1号架1区', '全新', 69.90, '2025-11-30', 1);
INSERT INTO `book_item` VALUES (11, '6C2CEA87-4C26-4E41-8083-CF71C087818F', 15, '一楼1号架2区', '全新', 99.90, '2025-11-30', 1);
INSERT INTO `book_item` VALUES (12, 'FF904287-F793-4960-9477-B1E49A90A85A', 15, '一楼1号架1区', '全新', 99.90, '2025-11-30', 2);
INSERT INTO `book_item` VALUES (13, '1A9DAD73-6AB7-4E48-95CF-C80BD84EA815', 14, '一楼2号架1区', '全新', 45.70, '2025-12-01', 1);
INSERT INTO `book_item` VALUES (14, 'FE26B1B5-1EFD-4BE2-BF5F-781E0A8D2740', 14, '一楼2号架1区', '全新', 45.70, '2025-11-30', 2);
INSERT INTO `book_item` VALUES (15, '6C48D122-AF07-42E7-8697-FC519074206B', 14, '一楼2号架1区', '全新', 45.70, '2025-11-30', 1);
INSERT INTO `book_item` VALUES (16, '8F5D198D-AEC6-42BB-A400-78F3512B96E0', 13, '四楼2号架1区', '全新', 33.30, '2025-12-02', 1);
INSERT INTO `book_item` VALUES (17, '2BD3999B-ACCD-4D4C-8CB1-C1BE00E6FF5A', 13, '四楼2号架1区', '全新', 33.30, '2025-12-02', 2);
INSERT INTO `book_item` VALUES (18, '4885C92A-EE01-4571-B26D-68F20A0F1BA4', 12, '三楼2号架1区', '全新', 19.90, '2025-12-03', 1);
INSERT INTO `book_item` VALUES (19, 'B923E9E9-EA4B-4FE4-9D9D-0C34FFDA2498', 12, '三楼2号架1区', '全新', 27.50, '2025-12-03', 1);

-- ----------------------------
-- Table structure for booklist
-- ----------------------------
DROP TABLE IF EXISTS `booklist`;
CREATE TABLE `booklist`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '书目表主键ID，自增',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '书目名称',
  `isbn` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'ISBN（国际标准书号）',
  `detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '简介',
  `author` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '作者',
  `publisher` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '出版商',
  `publish_year` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '出版年份',
  `publish_count` int(2) NULL DEFAULT NULL COMMENT '版次',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '书籍封面',
  `create_time` datetime NULL DEFAULT NULL COMMENT '书籍创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '书目信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of booklist
-- ----------------------------
INSERT INTO `booklist` VALUES (2, '悬疑小说', '978-7-123-45678-9', '十年前的雨夜凶案，唯一线索随嫌疑人坠崖戛然而止。菜鸟刑警林墨意外发现父亲遗留的旧案卷宗，页角模糊的指纹与新案现场痕迹完美重合——而父亲正是当年负责此案的警官，且在结案后离奇失踪。\n\n随着调查深入，看似无关的失踪案、匿名威胁信接连出现，身边同事、神秘线人似乎都藏着秘密。当林墨顺着指纹轨迹逼近真相，却发现自己早已陷入精心编织的陷阱，而父亲的失踪竟与他的身世紧密相连。\n\n雨夜再次降临，最后的线索指向废弃医院的地下室，尘封的真相即将破土而出，而等待他的，是救赎还是更深的黑暗？', '苏无名', '北京出版社', '2025', 1, 'http://localhost:21090/api/v1.0/book-manage-api/file/getFile?fileName=17647483230251.jpg', '2025-11-03 15:52:52');
INSERT INTO `booklist` VALUES (3, '鸡鸭名家', '979-8-1000-12345-6', '作为京派作家代表汪曾祺的作品，《鸡鸭名家》既是他对家乡风土人情与市井人物的追忆，也延续了京派文学关注日常、彰显人性之美的特点。书中对余老五、陆长庚等底层手艺人的刻画，让平凡职业里的匠心与坚守被看见，也让读者在平淡的叙事中感受生活本身的温度，是理解汪曾祺 “抒情的人道主义者” 创作特质的重要作品之一。', '汪曾祺', '长江出版社', '2024', 6, 'http://localhost:21090/api/v1.0/book-manage-api/file/getFile?fileName=17647509566102.jpg', '2025-12-05 16:37:41');
INSERT INTO `booklist` VALUES (4, '窄门', '978-7-9176-54321-0', '小说以主人公杰罗姆的第一人称回忆展开，讲述了他与表姐阿莉莎的悲剧爱情。两人自小青梅竹马、情愫暗生，都渴望为对方成为更优秀的人。但阿莉莎目睹母亲不忠，又看到妹妹朱丽叶因暗恋杰罗姆却无奈嫁给他人，过着无爱婚姻，这让她对尘世爱情和幸福充满恐惧。受严苛的清教思想影响，她将爱情视作通往上帝的阻碍，执意追求纯粹的精神之爱。她拒绝杰罗姆的求婚，选择仅以书信沟通，刻意保持距离，最后甚至离家出走。最终阿莉莎积忧成疾，孤独病逝在疗养院，而杰罗姆也终生未娶，将这份未圆满的爱情深埋心底。', '安德烈・纪德', '广东出版社', '2024', 3, 'http://localhost:21090/api/v1.0/book-manage-api/file/getFile?fileName=17647521587563.jpg', '2025-12-03 16:40:01');
INSERT INTO `booklist` VALUES (6, '历史不忍细看', '978-7-100-12345-6', '历史不忍细看 作者', '漓玉', '中国文艺出版社', '2025', 2, 'http://localhost:21090/api/v1.0/book-manage-api/file/getFile?fileName=17647531545724.jpg', '2025-11-13 17:16:08');
INSERT INTO `booklist` VALUES (7, '历史的镜子', '978-1-100-12345-6', '历史的镜子\n1. 推荐', '吴晗', '台海出版社', '2025', 2, 'http://localhost:21090/api/v1.0/book-manage-api/file/getFile?fileName=17647533731275.jpg', '2025-11-13 17:17:09');
INSERT INTO `booklist` VALUES (8, '历史地理学', '178-7-100-12345-6', '教培行业工具书', 'More', '文艺出版社', '2021', 2, 'http://localhost:21090/api/v1.0/book-manage-api/file/getFile?fileName=17647534330586.jpg', '2025-12-03 17:18:08');
INSERT INTO `booklist` VALUES (12, '活着', '978-7-111-75289-6', '《活着》讲述了人如何去承受巨大的苦难；讲述了眼泪的宽广和丰富；讲述了绝望的不存在；讲述了人是为了活着本身而活着的，而不是为了活着之外的任何事物而活着', '余华', '北京十月文艺出版社', '2024', 3, 'http://localhost:21090/api/v1.0/book-manage-api/file/getFile?fileName=17652056816158.jpg', '2025-12-08 22:55:11');
INSERT INTO `booklist` VALUES (13, '三体', '978-7-5080-9987-2', '当基础科学遭遇智子封锁，地球文明的命运悬于一线。天体物理学家叶文洁向深空发出信号，意外引来三体文明的窥视。四光年外，三体人面对恒纪元与乱纪元的残酷交替，将地球视为理想新家园。两个文明的碰撞由此展开，从“不要回答”的警告到面壁计划的惊天博弈，从水滴的死亡冲锋到黑暗森林法则的残酷揭示。', '刘慈欣', '重庆出版社', '2021', 2, 'http://localhost:21090/api/v1.0/book-manage-api/file/getFile?fileName=176520594121111.jpg', '2025-12-08 22:59:25');
INSERT INTO `booklist` VALUES (14, '五只小狼', '978-7-214-28675-9', '沈石溪，中国当代著名的动物小说作家，被誉为 动物小说大王 。他的动物小说作品精神内涵丰富厚重，兼具文学性、知识性和社会性，代表了当代动物小说创作领域的重要成就。', '沈石溪', '浙江少年儿童出版社', '2023', 1, 'http://localhost:21090/api/v1.0/book-manage-api/file/getFile?fileName=176520605028213.jpg', '2025-12-08 23:01:35');
INSERT INTO `booklist` VALUES (15, '红楼梦（上下两册，120回）', '979-8-7654-8901-5', '一部石头记，写尽世态炎凉。曹雪芹以贾、史、王、薛四大家族为背景，通过贾宝玉、林黛玉、薛宝钗的爱情悲剧，描绘了封建贵族由盛而衰的历史画卷。大观园内，诗酒风流掩不住“千红一窟，万艳同悲”的命运叹息。其人物刻画之精微、结构布局之宏大、文化内涵之深厚，被誉为中国古典小说的巅峰之作。字字看来皆是血，十年辛苦不寻常。', '曹雪芹', '商务印书局', '2021', 3, 'http://localhost:21090/api/v1.0/book-manage-api/file/getFile?fileName=176520614880214.jpg', '2025-12-08 23:03:44');
INSERT INTO `booklist` VALUES (16, '水浒传（上下册，全两册）', '978-7-80768-345-7', '《水浒传》的作者一般认为是元末明初小说家**施耐庵**（一说与罗贯中合著）。\n\n这部古典章回体小说是中国四大名著之一，以北宋末年宋江起义为背景，讲述了108位好汉在梁山泊聚义、劫富济贫、反抗官府的故事。书中塑造了武松、林冲、鲁智深等鲜活的英雄形象，情节跌宕起伏，语言通俗生动，既展现了农民起义的壮阔图景，也深刻反映了当时的社会矛盾，是中国白话小说的经典之作。', '施耐庵，罗贯中', '人民文学出版社', '2019', 4, 'http://localhost:21090/api/v1.0/book-manage-api/file/getFile?fileName=176520627977915.jpg', '2025-12-08 23:05:53');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别主键ID，自增',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '类别名',
  `level` int(1) NULL DEFAULT NULL COMMENT '层级',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父级ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 134 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '图书类别信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (6, '科学技术', 1, -1);
INSERT INTO `category` VALUES (8, '艺术类', 1, -1);
INSERT INTO `category` VALUES (9, '计算机技术与科学', 1, -1);
INSERT INTO `category` VALUES (14, '计算机', 2, 6);
INSERT INTO `category` VALUES (15, 'AI技术', 2, 6);
INSERT INTO `category` VALUES (16, '软件工程', 3, 14);
INSERT INTO `category` VALUES (34, 'C语言入门教程', 3, 9);
INSERT INTO `category` VALUES (80, '建筑学', 2, 6);
INSERT INTO `category` VALUES (89, '外国文学', 2, 86);
INSERT INTO `category` VALUES (114, '类别4', 4, 16);
INSERT INTO `category` VALUES (122, '古筝', 2, 8);
INSERT INTO `category` VALUES (123, '古代技艺', 2, 8);
INSERT INTO `category` VALUES (124, '笛子技法', 3, 123);
INSERT INTO `category` VALUES (128, '文学', 1, -1);
INSERT INTO `category` VALUES (129, '中国文学', 2, 128);
INSERT INTO `category` VALUES (130, '外国文学', 2, 128);
INSERT INTO `category` VALUES (131, '现代小说', 3, 129);
INSERT INTO `category` VALUES (132, '古代小说', 3, 129);
INSERT INTO `category` VALUES (133, '儿童文学', 2, 128);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '评论表主键ID，自增',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父级评论ID，构建评论的树形结构',
  `commenter_id` int(11) NULL DEFAULT NULL COMMENT '评论者ID',
  `replier_id` int(11) NULL DEFAULT NULL COMMENT '回复者ID',
  `content_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '内容类型，与内容ID配合使用',
  `content_id` int(11) NULL DEFAULT NULL COMMENT '内容ID，与内容类型配合使用',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '评论内容',
  `create_time` datetime NULL DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (49, NULL, 60, NULL, 'NOTICE', 1, '您可通过官网「系统状态」专区实时查看进度。若维护提前完成，系统将提前开放服务；若延期，我们将每 2 小时更新', '2025-12-06 20:56:35');
INSERT INTO `comment` VALUES (50, 49, 60, NULL, 'NOTICE', 1, '「系统状态」', '2025-12-06 20:56:42');
INSERT INTO `comment` VALUES (52, NULL, 60, NULL, 'BOOKLIST', 3, '点。书中对余老五、陆长庚', '2025-12-06 21:09:35');
INSERT INTO `comment` VALUES (54, NULL, 60, NULL, 'BOOKLIST', 2, '菜鸟刑警林墨意外发现父亲遗留的旧案卷宗，页角模糊的指纹与新案现场痕迹完美重合——而父亲正是当年负责此案的警官', '2025-12-06 21:24:29');

-- ----------------------------
-- Table structure for comment_like
-- ----------------------------
DROP TABLE IF EXISTS `comment_like`;
CREATE TABLE `comment_like`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论点赞表ID',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户ID，外键，关联用户表',
  `comment_id` int(11) NULL DEFAULT NULL COMMENT '评论ID，外键，关联评论表',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 86 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment_like
-- ----------------------------
INSERT INTO `comment_like` VALUES (85, 60, 49);

-- ----------------------------
-- Table structure for loans
-- ----------------------------
DROP TABLE IF EXISTS `loans`;
CREATE TABLE `loans`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '图书借阅信息表主键ID，自增',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户ID，外键，关联的是用户信息表',
  `book_item_id` int(11) NULL DEFAULT NULL COMMENT '馆藏书目ID，外键，关联的是馆藏书目表',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态（1-借阅中；2-已逾期；3-已归还）',
  `lend_date` date NULL DEFAULT NULL COMMENT '借出日期',
  `plan_return_date` date NULL DEFAULT NULL COMMENT '计划归还日期',
  `real_return_date` datetime NULL DEFAULT NULL COMMENT '实际归还时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '借阅时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE COMMENT '索引：用户ID',
  INDEX `idx_book_item_id`(`book_item_id`) USING BTREE COMMENT '索引：馆藏书目ID'
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '图书借阅信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of loans
-- ----------------------------
INSERT INTO `loans` VALUES (4, 60, 6, 3, '2025-12-07', '2025-12-20', '2025-12-07 19:46:28', '2025-12-07 19:46:03');
INSERT INTO `loans` VALUES (5, 60, 3, 1, '2025-11-13', '2025-12-26', NULL, '2025-11-13 20:32:37');
INSERT INTO `loans` VALUES (6, 60, 1, 1, '2025-12-07', '2025-12-30', NULL, '2025-12-07 20:34:24');
INSERT INTO `loans` VALUES (7, 60, 7, 1, '2025-12-07', '2025-12-17', NULL, '2025-12-07 20:36:37');
INSERT INTO `loans` VALUES (8, 61, 4, 1, '2025-12-07', '2025-12-25', NULL, '2025-12-07 21:45:43');
INSERT INTO `loans` VALUES (9, 60, 6, 1, '2025-12-07', '2025-12-11', NULL, '2025-12-07 21:47:29');
INSERT INTO `loans` VALUES (10, 64, 12, 1, '2025-12-08', '2025-12-26', NULL, '2025-12-08 23:11:07');
INSERT INTO `loans` VALUES (11, 64, 9, 1, '2025-12-08', '2025-12-22', NULL, '2025-12-08 23:11:18');
INSERT INTO `loans` VALUES (12, 64, 14, 1, '2025-12-08', '2025-12-21', NULL, '2025-12-08 23:11:25');
INSERT INTO `loans` VALUES (13, 64, 17, 1, '2025-12-08', '2025-12-16', NULL, '2025-12-08 23:11:58');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '公告信息表主键ID，自增',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '内容',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '公告信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, '系统更新公告', '<p>尊敬的用户：</p><p>为提升系统性能与服务质量，我们将于<span style=\"font-size: 16px;\"><strong>2025 年 12 月 10 日 00:00-06:00</strong></span>（北京时间）进行系统升级维护。本次维护为计划性操作，预计耗时 6 小时，期间系统将暂停服务，给您带来不便，我们深表歉意。</p><p>维护期间，您可通过官网「系统状态」专区实时查看进度。若维护提前完成，系统将提前开放服务；若延期，我们将每 2 小时更新一次进展。更新完成后，您可参考系统内「新功能指南」快速熟悉操作，或参与 12 月 11 日 15:00 的线上培训（报名入口将于维护后开放）。</p><p>感谢您的理解与支持！我们始终以提升用户体验为目标，若您有任何建议，欢迎通过「意见反馈」通道告知。</p><p>XX 系统运维团队2025 年 12 月 6 日</p>', '2025-12-06 16:28:14');
INSERT INTO `notice` VALUES (3, '系统升级通知', '<p><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\"><strong>标题</strong></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\">：服务器维护与系统升级公告<br></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\"><strong>发布日期</strong></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\">：2023年10月26日<br></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\"><strong>内容</strong></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\">：<br>尊敬的各位用户：<br>为提升服务稳定性，我们将于</span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\"><strong>10月28日凌晨2:00至6:00</strong></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\">进行系统升级。期间站点将暂停访问，请提前保存数据。升级后页面加载速度预计提升30%，感谢您的理解与支持！<br></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\"><strong>影响范围</strong></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\">：全站暂停服务4小时<br></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\"><strong>联系方式</strong></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\">：support@example.com</span></p>', '2025-12-06 20:09:49');
INSERT INTO `notice` VALUES (4, '安全漏洞修复紧急公告', '<p>内容</p><p style=\"text-align: start;\"><strong>标题</strong>：【紧急】密码安全提示与漏洞修复通知<br><strong>发布日期</strong>：2023年11月15日<br><strong>内容</strong><br>安全团队发现一处潜在账号风险漏洞（已修复）。为保障您的账户安全，请：</p><li style=\"text-align: start;\">立即修改登录密码</li><li style=\"text-align: start;\">开启双重验证（设置-安全中心）</li><li style=\"text-align: start;\">检查账户近期活动记录如发现异常操作，请联系客服处理。感谢您的配合！提示：本平台绝不会以任何形式索要密码</li><li style=\"text-align: start;\"></li><p style=\"text-align: start;\">内容</p><p style=\"text-align: start;\"><strong>标题</strong>：【紧急】密码安全提示与漏洞修复通知<br><strong>发布日期</strong>：2023年11月15日<br><strong>内容</strong><br>安全团队发现一处潜在账号风险漏洞（已修复）。为保障您的账户安全，请：</p><li style=\"text-align: start;\">立即修改登录密码</li><li style=\"text-align: start;\">开启双重验证（设置-安全中心）</li><li style=\"text-align: start;\">检查账户近期活动记录如发现异常操作，请联系客服处理。感谢您的配合！提示：本平台绝不会以任何形式索要密码</li><li style=\"text-align: start;\"></li><p style=\"text-align: start;\">内容</p><p style=\"text-align: start;\"><strong>标题</strong>：【紧急】密码安全提示与漏洞修复通知<br><strong>发布日期</strong>：2023年11月15日<br><strong>内容</strong><br>安全团队发现一处潜在账号风险漏洞（已修复）。为保障您的账户安全，请：</p><li style=\"text-align: start;\">立即修改登录密码</li><li style=\"text-align: start;\">开启双重验证（设置-安全中心）</li><li style=\"text-align: start;\">检查账户近期活动记录如发现异常操作，请联系客服处理。感谢您的配合！提示：本平台绝不会以任何形式索要密码</li><li style=\"text-align: start;\"></li><p style=\"text-align: start;\">内容</p><p style=\"text-align: start;\"><strong>标题</strong>：【紧急】密码安全提示与漏洞修复通知<br><strong>发布日期</strong>：2023年11月15日<br><strong>内容</strong><br>安全团队发现一处潜在账号风险漏洞（已修复）。为保障您的账户安全，请：</p><li style=\"text-align: start;\">立即修改登录密码</li><li style=\"text-align: start;\">开启双重验证（设置-安全中心）</li><li style=\"text-align: start;\">检查账户近期活动记录如发现异常操作，请联系客服处理。感谢您的配合！提示：本平台绝不会以任何形式索要密码</li><li style=\"text-align: start;\"></li><p style=\"text-align: start;\">内容</p><p style=\"text-align: start;\"><strong>标题</strong>：【紧急】密码安全提示与漏洞修复通知<br><strong>发布日期</strong>：2023年11月15日<br><strong>内容</strong><br>安全团队发现一处潜在账号风险漏洞（已修复）。为保障您的账户安全，请：</p><li style=\"text-align: start;\">立即修改登录密码</li><li style=\"text-align: start;\">开启双重验证（设置-安全中心）</li><li style=\"text-align: start;\">检查账户近期活动记录如发现异常操作，请联系客服处理。感谢您的配合！提示：本平台绝不会以任何形式索要密码</li><li style=\"text-align: start;\"></li><p style=\"text-align: start;\">内容</p><p style=\"text-align: start;\"><strong>标题</strong>：【紧急】密码安全提示与漏洞修复通知<br><strong>发布日期</strong>：2023年11月15日<br><strong>内容</strong><br>安全团队发现一处潜在账号风险漏洞（已修复）。为保障您的账户安全，请：</p><li style=\"text-align: start;\">立即修改登录密码</li><li style=\"text-align: start;\">开启双重验证（设置-安全中心）</li><li style=\"text-align: start;\">检查账户近期活动记录如发现异常操作，请联系客服处理。感谢您的配合！提示：本平台绝不会以任何形式索要密码</li><li style=\"text-align: start;\"></li><p style=\"text-align: start;\">内容</p><p style=\"text-align: start;\"><strong>标题</strong>：【紧急】密码安全提示与漏洞修复通知<br><strong>发布日期</strong>：2023年11月15日<br><strong>内容</strong><br>安全团队发现一处潜在账号风险漏洞（已修复）。为保障您的账户安全，请：</p><li style=\"text-align: start;\">立即修改登录密码</li><li style=\"text-align: start;\">开启双重验证（设置-安全中心）</li><li style=\"text-align: start;\">检查账户近期活动记录如发现异常操作，请联系客服处理。感谢您的配合！提示：本平台绝不会以任何形式索要密码</li><li style=\"text-align: start;\"></li><p style=\"text-align: start;\">内容</p><p style=\"text-align: start;\"><strong>标题</strong>：【紧急】密码安全提示与漏洞修复通知<br><strong>发布日期</strong>：2023年11月15日<br><strong>内容</strong><br>安全团队发现一处潜在账号风险漏洞（已修复）。为保障您的账户安全，请：</p><li style=\"text-align: start;\">立即修改登录密码</li><li style=\"text-align: start;\">开启双重验证（设置-安全中心）</li><li style=\"text-align: start;\">检查账户近期活动记录如发现异常操作，请联系客服处理。感谢您的配合！提示：本平台绝不会以任何形式索要密码</li>', '2025-12-06 20:09:56');
INSERT INTO `notice` VALUES (5, '用户体验优化通告', '<p><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\"><strong>标题</strong></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\">：界面改版与夜间模式上线<br></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\"><strong>发布日期</strong></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\">：2023年11月10日<br></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\"><strong>内容</strong></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\">：<br>为优化阅读体验，我们已完成以下更新：<br>1️⃣ </span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\"><strong>全新UI设计</strong></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\">：简化操作流程，页面更清爽<br>2️⃣ </span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\"><strong>新增夜间模式</strong></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\">：在「设置-主题」中一键切换<br>3️⃣ </span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\"><strong>字体自适应功能</strong></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\">：支持调节大小与间距<br>欢迎通过</span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\"><strong>意见反馈</strong></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\">通道提出建议，我们将持续改进！</span></p>', '2025-12-06 20:10:12');
INSERT INTO `notice` VALUES (6, '违规内容治理公告', '<p><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\"><strong>标题</strong></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\">：关于严厉打击恶意灌水行为的通知<br></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\"><strong>发布日期</strong></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\">：2023年11月5日<br></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\"><strong>内容</strong></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\">：<br>近期部分用户通过批量发布广告、重复灌水干扰社区秩序。即日起，平台将启用</span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\"><strong>AI内容审核系统</strong></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\">，对违规账号采取限流、封禁等措施。请遵守《社区公约》，共建友好交流环境。<br></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\"><strong>违规示例</strong></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\">：广告刷屏、引战言论、虚假信息<br></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\"><strong>举报渠道</strong></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\">：站内私信「社区管理员」</span></p>', '2025-12-06 20:10:25');
INSERT INTO `notice` VALUES (7, '新功能上线通知', '<p><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\"><strong>标题</strong></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\">：【重磅更新】AI助手功能正式上线！<br></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\"><strong>发布日期</strong></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\">：2023年11月1日<br></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\"><strong>内容</strong></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\">：<br>亲爱的用户：<br>我们推出了全新</span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\"><strong>AI智能助手</strong></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\">功能，支持实时问答、文档分析与创意生成！点击首页「AI实验室」即可体验。前1000名用户可免费试用高级权益30天，快来探索吧！<br></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\"><strong>适用对象</strong></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\">：所有注册用户<br></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\"><strong>注意事项</strong></span><span style=\"color: rgb(15, 17, 21); background-color: rgb(255, 255, 255); font-size: 16px;\">：需更新至最新版本客户端</span></p>', '2025-12-06 20:10:40');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户主键ID，自增',
  `account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账号',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `role` int(11) NULL DEFAULT NULL COMMENT '角色',
  `gender` tinyint(1) NULL DEFAULT NULL COMMENT '性别（1-女；2-男）',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `login_status` tinyint(1) NULL DEFAULT NULL COMMENT '登录状态（0-正常；1-封号）',
  `speak_status` tinyint(1) NULL DEFAULT NULL COMMENT '禁言状态（0-正常；1-禁言）',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '上一次登录时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间/登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'B站【程序员辰星】原创出品', '14e1b600b1fd579f47433b88e8d85291', 'http://localhost:21090/api/v1.0/book-manage-api/file/getFile?fileName=1764659584803Snipaste_2025-05-20_15-13-00.png', '1567643@qq.com', 1, 2, '1990-05-06', '16736666666', 0, 0, '2025-12-08 22:50:59', '2024-10-19 12:53:05');
INSERT INTO `user` VALUES (59, 'lrenzhenjiuhao', '李春然', '14e1b600b1fd579f47433b88e8d85291', 'http://localhost:21090/api/v1.0/book-manage-api/file/getFile?fileName=17652054502616.png', '123342@qq.com', 2, 1, '1985-07-11', '17687665323', 0, 1, '2025-12-08 22:27:57', '2025-05-28 17:54:48');
INSERT INTO `user` VALUES (60, 'zhouzhiruo', 'B站【程序员辰星】原创出品', '14e1b600b1fd579f47433b88e8d85291', 'http://localhost:21090/api/v1.0/book-manage-api/file/getFile?fileName=1764918282937Snipaste_2025-05-20_15-13-00.png', '23124231@qq.com', 2, 1, '1991-07-11', '16576666666', 0, 0, '2025-12-08 23:25:20', '2025-05-28 18:06:43');
INSERT INTO `user` VALUES (61, 'guihua', '桂花', '14e1b600b1fd579f47433b88e8d85291', 'http://localhost:21090/api/v1.0/book-manage-api/file/getFile?fileName=1765115181990Snipaste_2025-05-20_15-13-28.png', '14324@qq.com', 2, 1, NULL, NULL, 0, 0, '2025-12-07 21:45:28', '2025-05-29 14:13:04');
INSERT INTO `user` VALUES (62, 'zhangsan', '张三', '14e1b600b1fd579f47433b88e8d85291', 'http://localhost:21090/api/v1.0/scaffold-api/file/getFile?fileName=17642454803985.png', NULL, 2, NULL, NULL, NULL, 1, 0, '2025-11-16 17:56:33', '2025-05-29 15:39:59');
INSERT INTO `user` VALUES (63, 'zhangsan2', '追风筝的人', '14e1b600b1fd579f47433b88e8d85291', 'http://localhost:21090/api/v1.0/scaffold-api/file/getFile?fileName=17642454756628.png', '432432@qq.com', 2, 2, '1996-07-17', '17612442010', 1, 0, '2025-11-16 17:56:35', '2025-05-29 15:41:04');
INSERT INTO `user` VALUES (64, 'chenlili', '陈丽丽', '14e1b600b1fd579f47433b88e8d85291', 'http://localhost:21090/api/v1.0/book-manage-api/file/getFile?fileName=1765206644912Snipaste_2025-05-20_15-13-15.png', NULL, 2, 1, NULL, NULL, 0, 0, '2025-12-08 23:10:35', '2025-11-15 14:34:45');

-- ----------------------------
-- Table structure for user_action
-- ----------------------------
DROP TABLE IF EXISTS `user_action`;
CREATE TABLE `user_action`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户行为操作表主键ID，自增',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户ID，外键，关联的是用户表',
  `booklist_id` int(11) NULL DEFAULT NULL COMMENT '书目ID，外键，关联的是书目信息表',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '类型（1-收藏；2-点击；3-想看；4-停留时长）',
  `stay_time` bigint(20) NULL DEFAULT NULL COMMENT '停留时间（只有当行为操作类型是\'停留\'是才需要设置）',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_booklist_type_id`(`user_id`, `booklist_id`, `type`) USING BTREE COMMENT '联合索引：用户ID以及书目ID、类型'
) ENGINE = InnoDB AUTO_INCREMENT = 287 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户行为操作表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_action
-- ----------------------------
INSERT INTO `user_action` VALUES (1, 60, 3, 4, 1908, '2025-12-06 14:50:02');
INSERT INTO `user_action` VALUES (2, 60, 4, 4, 2547, '2025-12-06 14:50:40');
INSERT INTO `user_action` VALUES (3, 60, 2, 4, 2168, '2025-12-06 14:50:55');
INSERT INTO `user_action` VALUES (4, 60, 3, 4, 323808, '2025-12-06 14:56:20');
INSERT INTO `user_action` VALUES (5, 60, 3, 4, 10406, '2025-12-06 14:56:30');
INSERT INTO `user_action` VALUES (6, 60, 3, 4, 80388, '2025-12-06 14:57:51');
INSERT INTO `user_action` VALUES (7, 60, 3, 4, 53641, '2025-12-06 14:58:44');
INSERT INTO `user_action` VALUES (8, 60, 3, 4, 14144, '2025-12-06 14:58:58');
INSERT INTO `user_action` VALUES (9, 60, 3, 4, 7876, '2025-12-06 14:59:06');
INSERT INTO `user_action` VALUES (10, 60, 3, 4, 29087, '2025-12-06 14:59:35');
INSERT INTO `user_action` VALUES (11, 60, 3, 4, 28875, '2025-12-06 15:00:04');
INSERT INTO `user_action` VALUES (12, 60, 3, 4, 165912, '2025-12-06 15:02:51');
INSERT INTO `user_action` VALUES (13, 60, 3, 4, 12336, '2025-12-06 15:03:02');
INSERT INTO `user_action` VALUES (14, 60, 3, 4, 5934, '2025-12-06 15:03:08');
INSERT INTO `user_action` VALUES (15, 60, 3, 4, 2170, '2025-12-06 15:03:11');
INSERT INTO `user_action` VALUES (16, 60, 3, 4, 15844, '2025-12-06 15:03:26');
INSERT INTO `user_action` VALUES (17, 60, 3, 4, 24375, '2025-12-06 15:03:51');
INSERT INTO `user_action` VALUES (18, 60, 3, 4, 20377, '2025-12-06 15:04:11');
INSERT INTO `user_action` VALUES (19, 60, 3, 4, 14406, '2025-12-06 15:04:26');
INSERT INTO `user_action` VALUES (20, 60, 3, 4, 10049, '2025-12-06 15:04:36');
INSERT INTO `user_action` VALUES (21, 60, 3, 4, 8231, '2025-12-06 15:04:44');
INSERT INTO `user_action` VALUES (22, 60, 3, 4, 20108, '2025-12-06 15:05:04');
INSERT INTO `user_action` VALUES (23, 60, 3, 4, 66114, '2025-12-06 15:06:10');
INSERT INTO `user_action` VALUES (24, 60, 3, 4, 18585, '2025-12-06 15:06:29');
INSERT INTO `user_action` VALUES (25, 60, 3, 4, 19184, '2025-12-06 15:07:03');
INSERT INTO `user_action` VALUES (26, 60, 3, 4, 10047, '2025-12-06 15:07:15');
INSERT INTO `user_action` VALUES (27, 60, 3, 4, 14811, '2025-12-06 15:07:30');
INSERT INTO `user_action` VALUES (28, 60, 3, 4, 9952, '2025-12-06 15:07:40');
INSERT INTO `user_action` VALUES (31, 60, 3, 4, 18480, '2025-12-06 15:08:26');
INSERT INTO `user_action` VALUES (35, 60, 3, 4, 47697, '2025-12-06 15:10:29');
INSERT INTO `user_action` VALUES (36, 60, 3, 4, 8420, '2025-12-06 15:10:38');
INSERT INTO `user_action` VALUES (37, 60, 3, 4, 49309, '2025-12-06 15:11:27');
INSERT INTO `user_action` VALUES (38, 60, 3, 4, 6328, '2025-12-06 15:11:33');
INSERT INTO `user_action` VALUES (39, 60, 3, 4, 15932, '2025-12-06 15:11:49');
INSERT INTO `user_action` VALUES (40, 60, 3, 2, NULL, '2025-12-06 15:11:49');
INSERT INTO `user_action` VALUES (41, 60, 3, 4, 4241, '2025-12-06 15:11:54');
INSERT INTO `user_action` VALUES (42, 60, 3, 2, NULL, '2025-12-06 15:11:56');
INSERT INTO `user_action` VALUES (43, 60, 3, 4, 10226, '2025-12-06 15:12:06');
INSERT INTO `user_action` VALUES (44, 60, 3, 2, NULL, '2025-12-06 15:12:08');
INSERT INTO `user_action` VALUES (45, 60, 3, 2, NULL, '2025-12-06 15:12:48');
INSERT INTO `user_action` VALUES (46, 60, 3, 4, 600, '2025-12-06 15:12:49');
INSERT INTO `user_action` VALUES (47, 60, 3, 2, NULL, '2025-12-06 15:12:51');
INSERT INTO `user_action` VALUES (48, 60, 3, 4, 62515, '2025-12-06 15:13:54');
INSERT INTO `user_action` VALUES (49, 60, 3, 2, NULL, '2025-12-06 15:13:54');
INSERT INTO `user_action` VALUES (50, 60, 3, 4, 71602, '2025-12-06 15:15:05');
INSERT INTO `user_action` VALUES (51, 60, 3, 2, NULL, '2025-12-06 15:15:05');
INSERT INTO `user_action` VALUES (52, 60, 3, 4, 21469, '2025-12-06 15:15:27');
INSERT INTO `user_action` VALUES (53, 60, 3, 2, NULL, '2025-12-06 15:15:27');
INSERT INTO `user_action` VALUES (54, 60, 3, 4, 5050, '2025-12-06 15:15:32');
INSERT INTO `user_action` VALUES (55, 60, 3, 2, NULL, '2025-12-06 15:15:32');
INSERT INTO `user_action` VALUES (56, 60, 3, 4, 4988, '2025-12-06 15:15:37');
INSERT INTO `user_action` VALUES (57, 60, 3, 2, NULL, '2025-12-06 15:15:37');
INSERT INTO `user_action` VALUES (58, 60, 3, 4, 17352, '2025-12-06 15:15:54');
INSERT INTO `user_action` VALUES (59, 60, 3, 2, NULL, '2025-12-06 15:15:54');
INSERT INTO `user_action` VALUES (60, 60, 3, 4, 71159, '2025-12-06 15:17:05');
INSERT INTO `user_action` VALUES (61, 60, 3, 2, NULL, '2025-12-06 15:17:05');
INSERT INTO `user_action` VALUES (62, 60, 3, 4, 25485, '2025-12-06 15:17:31');
INSERT INTO `user_action` VALUES (63, 60, 3, 2, NULL, '2025-12-06 15:17:31');
INSERT INTO `user_action` VALUES (64, 60, 3, 4, 10659, '2025-12-06 15:17:41');
INSERT INTO `user_action` VALUES (65, 60, 3, 2, NULL, '2025-12-06 15:17:41');
INSERT INTO `user_action` VALUES (66, 60, 3, 4, 5914, '2025-12-06 15:17:47');
INSERT INTO `user_action` VALUES (67, 60, 3, 2, NULL, '2025-12-06 15:17:47');
INSERT INTO `user_action` VALUES (68, 60, 3, 4, 6772, '2025-12-06 15:17:54');
INSERT INTO `user_action` VALUES (69, 60, 3, 2, NULL, '2025-12-06 15:17:54');
INSERT INTO `user_action` VALUES (70, 60, 3, 4, 1326, '2025-12-06 15:17:55');
INSERT INTO `user_action` VALUES (71, 60, 3, 2, NULL, '2025-12-06 15:17:55');
INSERT INTO `user_action` VALUES (73, 60, 3, 2, NULL, '2025-12-06 15:18:00');
INSERT INTO `user_action` VALUES (74, 60, 3, 2, NULL, '2025-12-06 15:18:04');
INSERT INTO `user_action` VALUES (75, 60, 3, 2, NULL, '2025-12-06 15:18:05');
INSERT INTO `user_action` VALUES (76, 60, 3, 2, NULL, '2025-12-06 15:18:09');
INSERT INTO `user_action` VALUES (77, 60, 3, 4, 80708, '2025-12-06 15:19:31');
INSERT INTO `user_action` VALUES (78, 60, 3, 2, NULL, '2025-12-06 15:19:32');
INSERT INTO `user_action` VALUES (79, 60, 3, 4, 17840, '2025-12-06 15:19:47');
INSERT INTO `user_action` VALUES (80, 60, 3, 2, NULL, '2025-12-06 15:19:47');
INSERT INTO `user_action` VALUES (81, 60, 3, 4, 3488, '2025-12-06 15:19:51');
INSERT INTO `user_action` VALUES (82, 60, 3, 2, NULL, '2025-12-06 15:19:51');
INSERT INTO `user_action` VALUES (83, 60, 3, 4, 9612, '2025-12-06 15:20:00');
INSERT INTO `user_action` VALUES (84, 60, 3, 2, NULL, '2025-12-06 15:20:00');
INSERT INTO `user_action` VALUES (85, 60, 3, 4, 3230, '2025-12-06 15:20:04');
INSERT INTO `user_action` VALUES (86, 60, 3, 2, NULL, '2025-12-06 15:20:04');
INSERT INTO `user_action` VALUES (87, 60, 3, 3, NULL, '2025-12-06 15:20:07');
INSERT INTO `user_action` VALUES (88, 60, 3, 2, NULL, '2025-12-06 15:20:09');
INSERT INTO `user_action` VALUES (89, 60, 3, 2, NULL, '2025-12-06 15:20:13');
INSERT INTO `user_action` VALUES (90, 60, 3, 4, 2847, '2025-12-06 15:20:16');
INSERT INTO `user_action` VALUES (91, 60, 3, 2, NULL, '2025-12-06 15:20:18');
INSERT INTO `user_action` VALUES (92, 60, 3, 4, 19184, '2025-12-06 15:20:37');
INSERT INTO `user_action` VALUES (93, 60, 2, 2, NULL, '2025-12-06 15:20:38');
INSERT INTO `user_action` VALUES (94, 60, 2, 3, NULL, '2025-12-06 15:20:40');
INSERT INTO `user_action` VALUES (95, 60, 2, 4, 3934, '2025-12-06 15:20:42');
INSERT INTO `user_action` VALUES (96, 60, 2, 2, NULL, '2025-12-06 20:44:00');
INSERT INTO `user_action` VALUES (97, 60, 2, 4, 1781, '2025-12-06 20:44:02');
INSERT INTO `user_action` VALUES (98, 60, 3, 2, NULL, '2025-12-06 20:44:03');
INSERT INTO `user_action` VALUES (99, 60, 3, 4, 11164, '2025-12-06 20:44:14');
INSERT INTO `user_action` VALUES (100, 60, 3, 2, NULL, '2025-12-06 20:57:31');
INSERT INTO `user_action` VALUES (101, 60, 3, 4, 59858, '2025-12-06 20:58:31');
INSERT INTO `user_action` VALUES (102, 60, 3, 2, NULL, '2025-12-06 20:58:31');
INSERT INTO `user_action` VALUES (103, 60, 3, 4, 16442, '2025-12-06 20:58:48');
INSERT INTO `user_action` VALUES (104, 60, 3, 2, NULL, '2025-12-06 20:59:10');
INSERT INTO `user_action` VALUES (105, 60, 3, 4, 228194, '2025-12-06 21:02:36');
INSERT INTO `user_action` VALUES (106, 60, 3, 2, NULL, '2025-12-06 21:02:36');
INSERT INTO `user_action` VALUES (107, 60, 3, 4, 18880, '2025-12-06 21:02:55');
INSERT INTO `user_action` VALUES (108, 60, 3, 2, NULL, '2025-12-06 21:02:55');
INSERT INTO `user_action` VALUES (109, 60, 3, 4, 10940, '2025-12-06 21:03:06');
INSERT INTO `user_action` VALUES (110, 60, 3, 2, NULL, '2025-12-06 21:03:06');
INSERT INTO `user_action` VALUES (111, 60, 3, 2, NULL, '2025-12-06 21:03:55');
INSERT INTO `user_action` VALUES (112, 60, 3, 4, 11773, '2025-12-06 21:04:06');
INSERT INTO `user_action` VALUES (113, 60, 3, 2, NULL, '2025-12-06 21:04:08');
INSERT INTO `user_action` VALUES (114, 60, 3, 4, 37871, '2025-12-06 21:04:46');
INSERT INTO `user_action` VALUES (115, 60, 3, 2, NULL, '2025-12-06 21:04:46');
INSERT INTO `user_action` VALUES (116, 60, 3, 2, NULL, '2025-12-06 21:05:15');
INSERT INTO `user_action` VALUES (117, 60, 3, 4, 18022, '2025-12-06 21:05:33');
INSERT INTO `user_action` VALUES (118, 60, 3, 2, NULL, '2025-12-06 21:05:33');
INSERT INTO `user_action` VALUES (119, 60, 3, 4, 11771, '2025-12-06 21:05:45');
INSERT INTO `user_action` VALUES (120, 60, 3, 2, NULL, '2025-12-06 21:05:45');
INSERT INTO `user_action` VALUES (121, 60, 3, 4, 1863, '2025-12-06 21:05:46');
INSERT INTO `user_action` VALUES (122, 60, 3, 2, NULL, '2025-12-06 21:05:47');
INSERT INTO `user_action` VALUES (123, 60, 3, 2, NULL, '2025-12-06 21:05:56');
INSERT INTO `user_action` VALUES (124, 60, 3, 2, NULL, '2025-12-06 21:23:47');
INSERT INTO `user_action` VALUES (125, 60, 3, 4, 21144, '2025-12-06 21:24:08');
INSERT INTO `user_action` VALUES (126, 60, 3, 2, NULL, '2025-12-06 21:24:17');
INSERT INTO `user_action` VALUES (127, 60, 3, 4, 2755, '2025-12-06 21:24:20');
INSERT INTO `user_action` VALUES (128, 60, 2, 2, NULL, '2025-12-06 21:24:22');
INSERT INTO `user_action` VALUES (129, 60, 2, 4, 41637, '2025-12-06 21:25:03');
INSERT INTO `user_action` VALUES (130, 60, 4, 2, NULL, '2025-12-06 21:25:06');
INSERT INTO `user_action` VALUES (131, 60, 4, 4, 3682, '2025-12-06 21:25:09');
INSERT INTO `user_action` VALUES (132, 60, 3, 2, NULL, '2025-12-06 21:25:11');
INSERT INTO `user_action` VALUES (133, 60, 3, 4, 22522, '2025-12-06 21:25:34');
INSERT INTO `user_action` VALUES (134, 60, 3, 2, NULL, '2025-12-06 21:25:41');
INSERT INTO `user_action` VALUES (135, 60, 3, 4, 2250, '2025-12-06 21:25:43');
INSERT INTO `user_action` VALUES (136, 60, 2, 2, NULL, '2025-12-06 21:25:45');
INSERT INTO `user_action` VALUES (137, 60, 2, 4, 6398, '2025-12-06 21:25:52');
INSERT INTO `user_action` VALUES (138, 60, 3, 2, NULL, '2025-12-06 21:26:00');
INSERT INTO `user_action` VALUES (139, 60, 3, 4, 3436, '2025-12-06 21:26:03');
INSERT INTO `user_action` VALUES (140, 60, 2, 2, NULL, '2025-12-07 16:48:11');
INSERT INTO `user_action` VALUES (142, 60, 2, 4, 2981, '2025-12-07 16:48:14');
INSERT INTO `user_action` VALUES (143, 60, 2, 2, NULL, '2025-12-07 17:13:33');
INSERT INTO `user_action` VALUES (144, 60, 2, 4, 2632, '2025-12-07 17:13:36');
INSERT INTO `user_action` VALUES (145, 60, 3, 2, NULL, '2025-12-07 17:13:37');
INSERT INTO `user_action` VALUES (146, 60, 3, 4, 3379, '2025-12-07 17:13:40');
INSERT INTO `user_action` VALUES (147, 60, 4, 2, NULL, '2025-12-07 17:21:26');
INSERT INTO `user_action` VALUES (149, 60, 4, 4, 2762, '2025-12-07 17:21:28');
INSERT INTO `user_action` VALUES (150, 60, 2, 2, NULL, '2025-12-07 17:22:03');
INSERT INTO `user_action` VALUES (152, 60, 2, 4, 2609, '2025-12-07 17:22:06');
INSERT INTO `user_action` VALUES (153, 60, 3, 2, NULL, '2025-12-07 17:22:09');
INSERT INTO `user_action` VALUES (155, 60, 3, 4, 2124, '2025-12-07 17:22:11');
INSERT INTO `user_action` VALUES (156, 60, 4, 2, NULL, '2025-12-07 17:22:13');
INSERT INTO `user_action` VALUES (158, 60, 4, 4, 2194, '2025-12-07 17:22:15');
INSERT INTO `user_action` VALUES (159, 60, 3, 2, NULL, '2025-12-07 17:25:07');
INSERT INTO `user_action` VALUES (161, 60, 3, 4, 3623, '2025-12-07 17:25:10');
INSERT INTO `user_action` VALUES (162, 60, 4, 2, NULL, '2025-12-07 17:25:13');
INSERT INTO `user_action` VALUES (164, 60, 4, 4, 2013, '2025-12-07 17:25:15');
INSERT INTO `user_action` VALUES (165, 60, 4, 2, NULL, '2025-12-07 17:25:52');
INSERT INTO `user_action` VALUES (167, 60, 4, 4, 1493, '2025-12-07 17:25:53');
INSERT INTO `user_action` VALUES (168, 60, 4, 2, NULL, '2025-12-07 17:27:58');
INSERT INTO `user_action` VALUES (169, 60, 4, 1, NULL, '2025-12-07 17:27:58');
INSERT INTO `user_action` VALUES (170, 60, 4, 4, 1835, '2025-12-07 17:27:59');
INSERT INTO `user_action` VALUES (171, 60, 2, 2, NULL, '2025-12-07 17:31:24');
INSERT INTO `user_action` VALUES (173, 60, 2, 4, 1665, '2025-12-07 17:31:26');
INSERT INTO `user_action` VALUES (174, 60, 3, 2, NULL, '2025-12-07 17:34:17');
INSERT INTO `user_action` VALUES (175, 60, 3, 1, NULL, '2025-12-07 17:34:19');
INSERT INTO `user_action` VALUES (176, 60, 3, 4, 2156, '2025-12-07 17:34:19');
INSERT INTO `user_action` VALUES (177, 60, 2, 2, NULL, '2025-12-07 17:38:55');
INSERT INTO `user_action` VALUES (178, 60, 2, 4, 1901, '2025-12-07 17:38:57');
INSERT INTO `user_action` VALUES (179, 60, 3, 2, NULL, '2025-12-07 17:38:58');
INSERT INTO `user_action` VALUES (180, 60, 3, 4, 6395, '2025-12-07 17:39:05');
INSERT INTO `user_action` VALUES (181, 60, 3, 2, NULL, '2025-12-07 17:43:50');
INSERT INTO `user_action` VALUES (182, 60, 3, 4, 17710, '2025-12-07 17:44:08');
INSERT INTO `user_action` VALUES (183, 60, 2, 2, NULL, '2025-12-07 17:44:11');
INSERT INTO `user_action` VALUES (184, 60, 2, 4, 2172, '2025-12-07 17:44:13');
INSERT INTO `user_action` VALUES (185, 60, 3, 2, NULL, '2025-12-07 18:37:33');
INSERT INTO `user_action` VALUES (186, 60, 3, 4, 11856, '2025-12-07 18:37:45');
INSERT INTO `user_action` VALUES (187, 60, 3, 2, NULL, '2025-12-07 18:37:48');
INSERT INTO `user_action` VALUES (188, 60, 3, 4, 52053, '2025-12-07 18:38:40');
INSERT INTO `user_action` VALUES (189, 60, 3, 2, NULL, '2025-12-07 19:29:16');
INSERT INTO `user_action` VALUES (190, 60, 3, 4, 71810, '2025-12-07 19:30:28');
INSERT INTO `user_action` VALUES (191, 60, 3, 2, NULL, '2025-12-07 19:45:10');
INSERT INTO `user_action` VALUES (192, 60, 3, 4, 994, '2025-12-07 19:45:10');
INSERT INTO `user_action` VALUES (193, 60, 2, 2, NULL, '2025-12-07 19:45:12');
INSERT INTO `user_action` VALUES (194, 60, 2, 4, 1778, '2025-12-07 19:45:14');
INSERT INTO `user_action` VALUES (195, 60, 2, 2, NULL, '2025-12-07 19:45:56');
INSERT INTO `user_action` VALUES (196, 60, 2, 4, 8918, '2025-12-07 19:46:05');
INSERT INTO `user_action` VALUES (197, 60, 2, 2, NULL, '2025-12-07 19:46:17');
INSERT INTO `user_action` VALUES (198, 60, 2, 4, 8198, '2025-12-07 19:46:25');
INSERT INTO `user_action` VALUES (199, 60, 2, 2, NULL, '2025-12-07 19:46:30');
INSERT INTO `user_action` VALUES (200, 60, 2, 4, 5527, '2025-12-07 19:46:36');
INSERT INTO `user_action` VALUES (201, 60, 3, 2, NULL, '2025-12-07 20:32:32');
INSERT INTO `user_action` VALUES (202, 60, 3, 4, 79177, '2025-12-07 20:33:52');
INSERT INTO `user_action` VALUES (203, 60, 3, 2, NULL, '2025-12-07 20:33:52');
INSERT INTO `user_action` VALUES (204, 60, 3, 4, 22742, '2025-12-07 20:34:14');
INSERT INTO `user_action` VALUES (205, 60, 3, 2, NULL, '2025-12-07 20:34:14');
INSERT INTO `user_action` VALUES (206, 60, 3, 4, 2204, '2025-12-07 20:34:16');
INSERT INTO `user_action` VALUES (207, 60, 3, 2, NULL, '2025-12-07 20:34:16');
INSERT INTO `user_action` VALUES (208, 60, 3, 4, 7826, '2025-12-07 20:34:24');
INSERT INTO `user_action` VALUES (209, 60, 4, 2, NULL, '2025-12-07 20:35:47');
INSERT INTO `user_action` VALUES (210, 60, 4, 4, 40463, '2025-12-07 20:36:28');
INSERT INTO `user_action` VALUES (211, 60, 4, 2, NULL, '2025-12-07 20:36:30');
INSERT INTO `user_action` VALUES (212, 60, 4, 3, NULL, '2025-12-07 20:36:32');
INSERT INTO `user_action` VALUES (213, 60, 4, 4, 7437, '2025-12-07 20:36:37');
INSERT INTO `user_action` VALUES (214, 60, 4, 2, NULL, '2025-12-07 20:53:55');
INSERT INTO `user_action` VALUES (215, 60, 4, 2, NULL, '2025-12-07 20:54:13');
INSERT INTO `user_action` VALUES (216, 60, 4, 4, 1209, '2025-12-07 20:54:14');
INSERT INTO `user_action` VALUES (217, 60, 3, 2, NULL, '2025-12-07 21:25:11');
INSERT INTO `user_action` VALUES (218, 60, 3, 4, 2030, '2025-12-07 21:25:13');
INSERT INTO `user_action` VALUES (219, 60, 2, 2, NULL, '2025-12-07 21:25:16');
INSERT INTO `user_action` VALUES (220, 60, 2, 4, 3400, '2025-12-07 21:25:20');
INSERT INTO `user_action` VALUES (221, 60, 4, 2, NULL, '2025-12-07 21:34:35');
INSERT INTO `user_action` VALUES (222, 60, 4, 4, 2120, '2025-12-07 21:34:38');
INSERT INTO `user_action` VALUES (223, 60, 3, 2, NULL, '2025-12-07 21:35:13');
INSERT INTO `user_action` VALUES (224, 60, 3, 4, 7348, '2025-12-07 21:35:21');
INSERT INTO `user_action` VALUES (225, 60, 2, 2, NULL, '2025-12-07 21:39:14');
INSERT INTO `user_action` VALUES (226, 60, 2, 4, 3584, '2025-12-07 21:39:17');
INSERT INTO `user_action` VALUES (227, 60, 4, 2, NULL, '2025-12-07 21:41:12');
INSERT INTO `user_action` VALUES (228, 60, 4, 4, 93285, '2025-12-07 21:42:46');
INSERT INTO `user_action` VALUES (229, 61, 3, 2, NULL, '2025-12-07 21:45:37');
INSERT INTO `user_action` VALUES (230, 61, 3, 4, 6100, '2025-12-07 21:45:43');
INSERT INTO `user_action` VALUES (231, 61, 3, 2, NULL, '2025-12-07 21:45:48');
INSERT INTO `user_action` VALUES (232, 61, 3, 3, NULL, '2025-12-07 21:45:50');
INSERT INTO `user_action` VALUES (233, 61, 3, 1, NULL, '2025-12-07 21:45:54');
INSERT INTO `user_action` VALUES (234, 61, 3, 4, 7061, '2025-12-07 21:45:55');
INSERT INTO `user_action` VALUES (235, 61, 3, 2, NULL, '2025-12-07 21:46:01');
INSERT INTO `user_action` VALUES (236, 61, 3, 4, 10257, '2025-12-07 21:46:12');
INSERT INTO `user_action` VALUES (237, 61, 4, 2, NULL, '2025-12-07 21:46:36');
INSERT INTO `user_action` VALUES (238, 61, 4, 3, NULL, '2025-12-07 21:46:38');
INSERT INTO `user_action` VALUES (239, 61, 4, 4, 2717, '2025-12-07 21:46:39');
INSERT INTO `user_action` VALUES (240, 60, 3, 2, NULL, '2025-12-07 21:47:05');
INSERT INTO `user_action` VALUES (241, 60, 3, 4, 3000, '2025-12-07 21:47:08');
INSERT INTO `user_action` VALUES (242, 60, 2, 2, NULL, '2025-12-07 21:47:25');
INSERT INTO `user_action` VALUES (243, 60, 2, 4, 4182, '2025-12-07 21:47:29');
INSERT INTO `user_action` VALUES (244, 60, 2, 2, NULL, '2025-12-07 21:47:34');
INSERT INTO `user_action` VALUES (245, 60, 2, 4, 2519, '2025-12-07 21:47:36');
INSERT INTO `user_action` VALUES (246, 59, 3, 2, NULL, '2025-12-07 22:04:26');
INSERT INTO `user_action` VALUES (247, 59, 3, 4, 2374, '2025-12-07 22:04:29');
INSERT INTO `user_action` VALUES (248, 60, 11, 2, NULL, '2025-12-08 15:57:20');
INSERT INTO `user_action` VALUES (249, 60, 11, 2, NULL, '2025-12-08 15:57:41');
INSERT INTO `user_action` VALUES (250, 60, 11, 4, 1087, '2025-12-08 15:57:42');
INSERT INTO `user_action` VALUES (251, 60, 8, 2, NULL, '2025-12-08 16:01:57');
INSERT INTO `user_action` VALUES (252, 60, 8, 4, 3013, '2025-12-08 16:02:00');
INSERT INTO `user_action` VALUES (253, 60, 4, 2, NULL, '2025-12-08 16:02:02');
INSERT INTO `user_action` VALUES (254, 60, 4, 4, 3548, '2025-12-08 16:02:05');
INSERT INTO `user_action` VALUES (255, 60, 4, 2, NULL, '2025-12-08 16:42:14');
INSERT INTO `user_action` VALUES (256, 60, 4, 4, 1925, '2025-12-08 16:42:16');
INSERT INTO `user_action` VALUES (257, 60, 2, 2, NULL, '2025-12-08 17:01:47');
INSERT INTO `user_action` VALUES (258, 60, 2, 4, 4872, '2025-12-08 17:01:51');
INSERT INTO `user_action` VALUES (259, 60, 4, 2, NULL, '2025-12-08 21:15:04');
INSERT INTO `user_action` VALUES (260, 60, 4, 4, 2377, '2025-12-08 21:15:06');
INSERT INTO `user_action` VALUES (261, 60, 4, 2, NULL, '2025-12-08 21:27:21');
INSERT INTO `user_action` VALUES (262, 60, 4, 4, 1269, '2025-12-08 21:27:22');
INSERT INTO `user_action` VALUES (263, 60, 8, 2, NULL, '2025-12-08 22:27:34');
INSERT INTO `user_action` VALUES (264, 60, 8, 1, NULL, '2025-12-08 22:27:35');
INSERT INTO `user_action` VALUES (265, 60, 8, 3, NULL, '2025-12-08 22:27:36');
INSERT INTO `user_action` VALUES (266, 60, 8, 4, 2199, '2025-12-08 22:27:36');
INSERT INTO `user_action` VALUES (267, 64, 15, 2, NULL, '2025-12-08 23:10:54');
INSERT INTO `user_action` VALUES (268, 64, 15, 1, NULL, '2025-12-08 23:10:57');
INSERT INTO `user_action` VALUES (269, 64, 15, 3, NULL, '2025-12-08 23:10:58');
INSERT INTO `user_action` VALUES (270, 64, 15, 4, 13077, '2025-12-08 23:11:07');
INSERT INTO `user_action` VALUES (271, 64, 16, 2, NULL, '2025-12-08 23:11:12');
INSERT INTO `user_action` VALUES (272, 64, 16, 1, NULL, '2025-12-08 23:11:13');
INSERT INTO `user_action` VALUES (273, 64, 16, 3, NULL, '2025-12-08 23:11:14');
INSERT INTO `user_action` VALUES (274, 64, 16, 4, 5810, '2025-12-08 23:11:18');
INSERT INTO `user_action` VALUES (275, 64, 14, 2, NULL, '2025-12-08 23:11:20');
INSERT INTO `user_action` VALUES (276, 64, 14, 1, NULL, '2025-12-08 23:11:21');
INSERT INTO `user_action` VALUES (277, 64, 14, 3, NULL, '2025-12-08 23:11:22');
INSERT INTO `user_action` VALUES (278, 64, 14, 4, 4977, '2025-12-08 23:11:25');
INSERT INTO `user_action` VALUES (279, 64, 15, 2, NULL, '2025-12-08 23:11:31');
INSERT INTO `user_action` VALUES (280, 64, 15, 4, 3348, '2025-12-08 23:11:35');
INSERT INTO `user_action` VALUES (281, 64, 13, 2, NULL, '2025-12-08 23:11:53');
INSERT INTO `user_action` VALUES (282, 64, 13, 4, 5248, '2025-12-08 23:11:58');
INSERT INTO `user_action` VALUES (283, 64, 13, 2, NULL, '2025-12-08 23:12:01');
INSERT INTO `user_action` VALUES (284, 64, 13, 1, NULL, '2025-12-08 23:12:02');
INSERT INTO `user_action` VALUES (285, 64, 13, 3, NULL, '2025-12-08 23:12:02');
INSERT INTO `user_action` VALUES (286, 64, 13, 4, 2174, '2025-12-08 23:12:03');

SET FOREIGN_KEY_CHECKS = 1;
