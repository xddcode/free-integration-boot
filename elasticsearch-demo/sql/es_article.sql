/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : es_demo

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2021-04-01 14:08:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `es_article`
-- ----------------------------
DROP TABLE IF EXISTS `es_article`;
CREATE TABLE `es_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `summary` varchar(255) DEFAULT NULL COMMENT '简介',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `author` varchar(10) DEFAULT NULL COMMENT '作者',
  `is_end` tinyint(1) DEFAULT NULL COMMENT '是否完结 0否 1是',
  `is_publish` tinyint(1) DEFAULT NULL COMMENT '是否发版 0否 1是',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of es_article
-- ----------------------------
INSERT INTO `es_article` VALUES ('1', '吞噬星空', '《吞噬星空》，是一部东方科幻类型的网络小说，小说签约授权首发连载于起点中文网。小说已经完本，作者是我吃西红柿。', '星空中。\r\n“这颗星球，通体土黄色，没有任何生命存在，直径21000公里，咦，竟然蕴含‘星泪金’矿脉，真是天助我也，将这颗星球吞噬掉后，我的实力应该能恢复到受伤前的80%。”脸色苍白的罗峰盘膝坐在一颗飞行的陨石上，遥看远处的一颗无生命存在的行星。 [1] \r\n番茄第六部小说《吞噬星空》，将为读者展现出一个浩瀚广阔，神秘莫测的未来世界。', '我吃西红柿', '1', '1', '2021-04-01 10:39:20');
INSERT INTO `es_article` VALUES ('2', '斗罗大陆', '《斗罗大陆》是唐家三少创作的穿越玄幻小说，2008年12月14日-2009年12月13日首发于起点中文网，2009年5月首次出版。', '小说主要描绘了一个名叫斗罗大陆的武魂世界。唐门外门弟子唐三，因偷学内门绝学而为唐门所不容，跳崖明志时却发现穿越到另一个世界，即斗罗大陆的圣魂村。这里没有魔法，没有武术，没有战斗力，却有神奇的武魂。这里的每个人在6岁的时候，都会在武魂殿中在魂师的帮助下进行武魂觉醒。武魂有动物，有植物，有器物，武魂可以辅助人们的日常生活，有特殊天赋的人可以用之修炼并进行战斗。 [7]  唐三一步步修炼武魂，由人修炼为神，最终铲除了斗罗大陆上的邪恶力量，报了杀母之仇，成为斗罗大陆最强者的故事。', '唐家三少', '1', '1', '2021-04-01 13:46:29');
INSERT INTO `es_article` VALUES ('3', '剑来', '大千世界，无奇不有。 我陈平安，唯有一剑，可搬山，倒海，降妖，镇魔，敕神，摘星，断江，摧城，开天！', '大千世界，无奇不有。 我陈平安，唯有一剑，可搬山，倒海，降妖，镇魔，敕神，摘星，断江，摧城，开天！ 我叫陈平安，平平安安的平安。我是一名剑客。', '烽火戏诸侯', '0', '1', '2021-04-01 13:47:34');
INSERT INTO `es_article` VALUES ('4', '诛仙', '《诛仙》是当代作家萧鼎创作的一部长篇小说。该小说约创作于2003年至2007年。2003年3月在中国台湾开始出版，2005年4月中国大陆朝华出版社出版了前六册，后两册开始转由花山文艺出版社出版。', '草庙村普通少年张小凡在机缘巧合下认识了普智高僧，普智临终前将天音寺不外传的真法“大梵般若”传授给小凡，希望能在张小凡身上圆自己佛道双修、参透生死的梦想。后来草庙村遭到血腥屠杀，小凡和林惊羽被名门正派青云门收留。资质愚钝的张小凡进入“大竹峰”后，武艺修行进展缓慢，在一次伐竹过程中，为追一只三眼灵猴，获得了一件以自己精血炼成的至凶至邪之法宝——“烧火棍”。', '萧鼎', '1', '1', '2021-04-01 13:47:37');
INSERT INTO `es_article` VALUES ('5', '斗破苍穹', '《斗破苍穹》是一本连载于起点中文网的古装玄幻小说，作者是起点白金作家天蚕土豆（李虎），已完结。这里是属于斗气的世界，没有花俏艳丽的魔法，有的，仅仅是繁衍到巅峰的斗气！', '萧炎，主人公，萧家历史上空前绝后的斗气修炼天才。4岁就开始修炼斗之气，10岁拥有了九段斗之气，11岁突破十段斗之气，一跃成为家族百年来最年轻的斗者。然而在12岁那年，他却“丧失”了修炼能力，只拥有三段斗之气。整整三年时间，家族冷落，旁人轻视，被未婚妻退婚……种种打击接踵而至。', '天蚕土豆', '1', '1', '2021-04-01 13:48:20');
INSERT INTO `es_article` VALUES ('6', '九鼎记', '小说讲述了现代世界代号为“狼”的著名杀手、一代形意拳宗师滕青山死后穿越来到了“九州”，经过重重磨难最终成为至强者，最终撕裂虚空回到地球的故事。', '自禹皇五斧劈山，统一天下，划分九州，立九鼎后，这片大地便被称为九州。禹皇离世后，天下便纷争不断。千年后，一代天骄秦岭天帝横空出世，曾一掌令百丈宽的雁江江水断流，凭绝世武力，最终得以一统天下，可当秦岭天帝离世后，天下同样大乱，数千年来，没有再度统一过。而现代世界的一代形意宗师‘滕青山’却来到了这样的世界。', '我吃西红柿', '1', '1', '2021-04-01 13:50:59');
INSERT INTO `es_article` VALUES ('7', '冰火魔厨', '小说讲述了一位天生具有冰火同源体质的少年融念冰，为了给父母报仇而苦练魔法，并以魔法入厨，探寻厨艺巅峰的故事。', '年幼的融念冰随父前往冰神塔救母，谁知救母不成，父亲也陷落于冰神塔中，只有念冰得以身免。拿着父亲和母亲留给他的两颗宝石，在冰雪祭祀的追杀下，他跳入了湍急的天青河中。天意将他带到了桃花林中，在那里，他遇到了一个人，鬼厨查极。八年刻苦的锻炼，使他拥有了顶尖厨艺，但这时，查极却突然死了。临死前，查极告诉他一句话，八年练厨，八年悟厨，冰火同源，以魔入厨，以魔法入厨艺，一代魔厨走入了命运的轮回。', '唐家三少', '1', '1', '2021-04-01 13:51:02');
INSERT INTO `es_article` VALUES ('8', '凡人修仙传', '小说讲述了一个普通的山村穷小子，偶然之下，跨入到一个江湖小门派，虽然资质平庸，但依靠自身努力和合理算计最后修炼成仙的故事。', '一个普通的山村穷小子，偶然之下，跨入到一个江湖小门派，成了一名记名弟子。他以这样身份，如何在门派中立足,如何以平庸的资质进入到修仙者的行列，从而笑傲三界之中！又如何以平庸的资质，进入到修仙者的行列？和其他巨枭魔头、仙宗仙师并列于山海内外？修仙世界尔虞我诈、弱肉强食，大道漫漫，仙途凶险，看普通山村少年韩立如何艰难修仙。虽然资质平庸，但依靠自身努力和合理算计修炼成仙。修仙的过程极为不易，不仅需要克服自身的种种缺陷，同时还要接受敌人和天地的考验，经过重重“跋涉”才能得道。', '忘语', '1', '1', '2021-04-01 13:51:04');
INSERT INTO `es_article` VALUES ('9', '盘龙', '《盘龙》是一本首发于起点中文网的西方玄幻类小说，作者是我吃西红柿，2009年6月12日完结。 [1]  小说讲述了主人公林雷无意中从祖宅拣到一枚神奇的戒指，而后踏上了梦幻之旅的故事。', '楼房大小的血睛鬃毛狮，力大无穷的紫睛金毛猿，毁天灭地的九头蛇皇，携带着毁灭雷电的恐怖雷龙……这里无奇不有，这是一个广博的魔幻世界。强者可以站在黑色巨龙的头顶遨游天际，恐怖的魔法可以焚烧江河，可以毁灭城池，可以夷平山岳……这本书，讲述了一个拥有‘盘龙戒指’的少年的梦幻旅程。', '我吃西红柿', '1', '1', '2021-04-01 13:51:06');
INSERT INTO `es_article` VALUES ('10', '校花的贴身高手', '故事讲述了从大山里走出来的绝世高手，一块能预知未来的神秘玉佩……身负重任，追校花！', '故事讲述了从大山里走出来的绝世高手，一块能预知未来的神秘玉佩……身负重任，追校花！还是奉校花老爸之命！虽然林逸很不想跟这位难伺候的大小姐打交道，但是长辈之命难违抗，他不得不千里迢迢地转学到松山市，给大小姐鞍前马后地当跟班……于是史上最牛跟班出现了。', '鱼人二代', '1', '1', '2021-04-01 13:51:08');
INSERT INTO `es_article` VALUES ('11', '赘婿', '本书主要讲述了主角从现代金融界巨头的身份回到了古代，进入一个商贾之家最没地位的赘婿身体后，涉及到一系列家国天下事的故事。', '武朝末年，岁月峥嵘，天下纷乱，金辽相抗，局势动荡，百年屈辱，终于望见结束的第一缕曙光，天祚帝、完颜阿骨打、吴乞买，成吉思汗铁木真、札木合、赤老温、木华黎、博尔忽、博尔术、秦桧、岳飞、李纲、种师道、唐恪、吴敏、耿南仲、张邦昌，忠臣与奸臣的较量，英雄与枭雄的博弈，胡虏南下，百万铁骑叩雁门，江山沦陷，生灵涂炭，一个国家与民族百年的屈辱与抗争，先行者的哭泣、呐喊与悲怆……', '愤怒的香蕉', '1', '1', '2021-04-01 13:54:05');
INSERT INTO `es_article` VALUES ('12', '雪中悍刀行', '该小说讲述一个关于庙堂权争与刀剑交错的时代，一个暗潮涌动粉墨登场的江湖。', '有个白狐儿脸，佩双刀绣冬春雷，要做那天下第一。湖底有白发老魁爱吃荤。缺门牙老仆背剑匣。山上有个骑青牛的年轻师叔祖，不敢下山。有个骑熊猫扛向日葵不太冷的少女杀手。 这个江湖，高人出行要注重出尘装扮，女侠行走江湖要注意培养人气，宗派要跟庙堂打好关系。 而主角，则潇洒带刀，把江湖捅了一个通透。 小二，上酒～', '烽火戏诸侯', '1', '1', '2021-04-01 13:55:33');