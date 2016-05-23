/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.11 : Database - aal
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`yqtq` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `yqtq`;

DROP TABLE IF EXISTS `t_admin`;

/** 系统管理员表 **/
CREATE TABLE `t_admin` (
  `id` bigint(32) NOT NULL auto_increment,
  `username` varchar(50) not null unique comment '登录名',
  `password` varchar(50) not null,
  `create_date` bigint,
  `modify_date` bigint,
  `last_login_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理员表';

insert into `t_admin` (`username`,`password`) values ("admin","21232F297A57A5A743894A0E4A801FC3");

DROP TABLE IF EXISTS `t_role`;

/** 角色表 **/
CREATE TABLE `t_role` (
  `id` bigint(32) NOT NULL auto_increment,
  `name` varchar(50) not null unique comment '角色',
  `admin_id` bigint comment '操作员',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

DROP TABLE IF EXISTS `t_module`;

/** 模块表 **/
CREATE TABLE `t_module` (
  `id` bigint(32) NOT NULL auto_increment,
  `name` varchar(50) not null unique comment '模块名称',
  `url` bigint comment '模块url',
  `description`varchar(2000) comment '描述',
  `admin_id` bigint comment '操作员',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模块表';

DROP TABLE IF EXISTS `t_user_role`;

/** 用户角色关联关系表 **/
CREATE TABLE `t_user_role` (
  `id` bigint(32) NOT NULL auto_increment,
  `admin_id` bigint(32) comment '管理员ID',
  `role_id` bigint(32) comment '角色ID', 
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联关系表';


DROP TABLE IF EXISTS `t_role_module`;

/** 角色模块关联关系表 **/
CREATE TABLE `t_role_module` (
  `id` bigint(32) NOT NULL auto_increment,
  `role_id` bigint(32) comment '角色ID', 
  `module_id` bigint (32) comment '模块ID',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色模块关联关系表';


DROP TABLE IF EXISTS `t_user_login`;

/** 用户登录信息表 **/
CREATE TABLE `t_user_login` (
  `id` bigint(32) NOT NULL auto_increment,
  `username` varchar(50) not null unique comment '登录名',
  `password` varchar(50) not null,
  `create_date` bigint,
  `modify_date` bigint,
  `last_login_date` bigint,
  `ip_address` varchar(20) comment "登录IP地址",
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录信息表';

DROP TABLE IF EXISTS `t_user_info`;

/** 用户详细信息表 **/
CREATE TABLE `t_user_info` (
  `id` bigint(32) NOT NULL auto_increment,
  `user_id` bigint(32) not null unique comment '关联用户登录表ID',
  `mobile` varchar(15) not null unique comment '手机号，用于用户登录',
  `password` varchar(50) not null,
  `nickname` varchar(50) not null default '' comment '昵称',
  `age` int(2) DEFAULT 0 comment '年龄',
  `heigth` double default 0 comment '身高',
  `weight` double DEFAULT 0 comment '体重',
  `position` varchar(2) comment '位置',
  `credibility` int(11) DEFAULT 0 comment '信誉度',
  `vip_level` int(2) DEFAULT 0 comment '会员等级',
  `integral` int(11) DEFAULT 0 comment '积分',
  `experience` int(11) DEFAULT 0 comment '经验',
  `avater` varchar(200) DEFAULT '' comment '头像URL',
  `provice_id` bigint comment '省份ID',
  `vip_end_date` bigint comment '会员结束时间',
  `city_id` bigint comment '城市ID',
  `status` int(2) default 0 comment '用户状态 0:正常 1:冻结',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户详细信息表';

DROP TABLE IF EXISTS `t_user_vip`;

/** 用户会员记录表 **/
CREATE TABLE `t_user_vip` (
  `id` bigint(32) NOT NULL auto_increment,
  `user_id` bigint(32) not null unique comment '关联用户登录表ID',
  `duration` bigint comment '充值时长',
  `vip_end_date` bigint comment '会员结束时间',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户会员记录表';


DROP TABLE IF EXISTS `t_stadium_user`;

/** 球场主信息表 **/
CREATE TABLE `t_stadium_user` (
  `id` bigint(32) NOT NULL auto_increment,
  `username` bigint comment '登录名',
  `password` bigint comment '密码',
  `mobile` varchar(20) DEFAULT '' comment '电话',
  `reserve_money` double DEFAULT 0 comment '预定总金额',
  `withdraw_money` double DEFAULT 0 comment '体现总金额',
  `balance` double DEFAULT 0 comment '余额',
  `city_id` bigint comment '城市ID',
  `status` int(2) comment '状态 0:正常 1:冻结',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='球场主信息表';

DROP TABLE IF EXISTS `t_stadium_user_withdraw`;

/** 球场主提现表 **/
CREATE TABLE `t_stadium_user_withdraw` (
  `id` bigint(32) NOT NULL auto_increment,
  `stadium_user_id` bigint comment '球场主ID',
  `withdraw_money` bigint comment '提现金额',
  `status` int(2) comment '状态 0:未转账 1:转账中 2:已转账 3:驳回',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='球场主提现表';

DROP TABLE IF EXISTS `t_reserve`;

/** 球场(比赛)预定表 **/
CREATE TABLE `t_reserve` (
  `id` bigint(32) NOT NULL auto_increment,
  `user_id` bigint comment '预定用户ID',
  `stadium_id` bigint comment '预定球场ID',
  `city_id` bigint comment '城市ID',
  `match_type` int(2) comment '赛制',
  `price` double comment '价格',
  `payment` int(2) comment '付款方式', 
  `insurance_id` bigint comment '保险ID',
  `type` int(2) DEFAULT 0 comment '球赛类型 0:散客 1:球队',
  `start_date` bigint,
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='球场(比赛)预定表';

DROP TABLE IF EXISTS `t_insurance`;

/** 保险表 **/
CREATE TABLE `t_insurance` (
  `id` bigint(32) NOT NULL auto_increment,
  `user_id` bigint comment '预定用户ID',
  `reserve_id` bigint comment '预定球场ID',
  `money` bigint comment '金额',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保险表';

DROP TABLE IF EXISTS `t_user_reserve_join`;

/** 球场(比赛.散客)加入表 **/
CREATE TABLE `t_user_reserve_join` (
  `id` bigint(32) NOT NULL auto_increment,
  `user_id` bigint comment '预定用户ID',
  `reserve_id` bigint comment '预定球场ID',
  `status` int(2) comment '状态 0:未处理 1:同意 2:拒绝',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='球场(比赛.散客)加入表';


DROP TABLE IF EXISTS `t_team`;

/** 球队表 **/
CREATE TABLE `t_team` (
  `id` bigint(32) NOT NULL auto_increment,
  `name` bigint comment '球队名',
  `city_id` bigint comment '城市ID',
  `slogan` varchar(200) comment '口号',
  `avater` varchar(200) comment '对标',
  `leader_user_id` bigint comment '队长ID',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='球队表';


DROP TABLE IF EXISTS `t_team_member`;

/** 球队成员表 **/
CREATE TABLE `t_team_member` (
  `id` bigint(32) NOT NULL auto_increment,
  `user_id` bigint comment '用户ID',
  `team_id` bigint comment '球队ID',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='球队成员表';

DROP TABLE IF EXISTS `t_team_race`;

/** 赛事表(球队) **/
CREATE TABLE `t_team_race` (
  `id` bigint(32) NOT NULL auto_increment,
  `home_team_id` bigint comment '主队ID',
  `visiting_team_id` bigint comment '客队ID',
  `city_id` bigint comment '城市ID',
  `stadium_id` bigint comment '球场ID',
  `start_date` bigint comment '比赛开始时间',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='赛事表(球队)';

DROP TABLE IF EXISTS `t_stadium`;

/** 球场表 **/
CREATE TABLE `t_stadium` (
  `id` bigint(32) NOT NULL auto_increment,
  `city_id` bigint comment '城市ID',
  `stadium_user_id` bigint comment '球场主ID',
  `name` bigint comment '球场名称',
  `type` int(2) comment '球场类型 0:私人球场 1:公共球场',
  `description` varchar(2000) DEFAULT '' comment '球场信息',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='球场表';

DROP TABLE IF EXISTS `t_stadium_sub`;

/** 场地表 **/
CREATE TABLE `t_stadium_sub` (
  `id` bigint(32) NOT NULL auto_increment,
  `stadium_id` bigint comment '球场ID',
  `code` varchar(20) comment '场地编号',
  `type` int(2) comment '球场类型 N人制',
  `price` double DEFAULT 0 comment '价格/小时',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='场地表';


DROP TABLE IF EXISTS `t_host_race`;

/** 赛事表(举办) **/
CREATE TABLE `t_host_race` (
  `id` bigint(32) NOT NULL auto_increment,
  `name` varchar(50) comment '赛事名称',
  `avater` varchar(200) comment '封面',
  `stadium_id` bigint comment '球场ID',
  `description` varchar(2000) comment '介绍',
  `start_date` bigint comment '比赛开始时间',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='赛事表(举办)';

DROP TABLE IF EXISTS `t_host_race_join`;

/** 赛事报名表(举办) **/
CREATE TABLE `t_host_race_join` (
  `id` bigint(32) NOT NULL auto_increment,
  `host_race_id` bigint comment '赛事ID',
  `team_id` bigint comment '球队ID',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='赛事报名表(举办)';

DROP TABLE IF EXISTS `t_girl`;

/** 宝贝表 **/
CREATE TABLE `t_girl` (
  `id` bigint(32) NOT NULL auto_increment,
  `city_id` bigint comment '城市ID',
  `name` varchar(20) DEFAULT '' comment '宝贝名称',
  `price` double DEFAULT 0 comment '价格 /小时',
  `interest` varchar(200) DEFAULT '' comment '兴趣',
  `favorite_team` varchar(200) DEFAULT '' comment '喜欢球队',
  `profession` varchar(100) DEFAULT '' comment '职业',
  `label` varchar(100) DEFAULT '' comment '签名',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='宝贝表';


DROP TABLE IF EXISTS `t_girl_image`;

/** 宝贝图片表 **/
CREATE TABLE `t_girl_image` (
  `id` bigint(32) NOT NULL auto_increment,
  `girl_id` bigint comment '宝贝ID',
  `type` int(2) DEFAULT 0 comment '图片类型 0:封面 1:相册',
  `url` varchar(200) DEFAULT '' comment '图片url',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='宝贝图片表';



DROP TABLE IF EXISTS `t_girl_user`;

/** 宝贝约看表 **/
CREATE TABLE `t_girl_user` (
  `id` bigint(32) NOT NULL auto_increment,
  `girl_id` bigint comment '宝贝ID',
  `user_id` bigint comment '用户ID',
  `stadium_id` bigint comment '球场ID',
  `start_date` bigint comment '预约时间',
  `race_id` bigint comment '赛事ID',
  `price` double comment '价格',
  `tip` double comment '小费',
  `duration` int(2) comment '时长',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='宝贝约看表';

DROP TABLE IF EXISTS `t_watching_race`;

/** 直播看球表 **/
CREATE TABLE `t_watching_race` (
  `id` bigint(32) NOT NULL auto_increment,
  `name` varchar(50) not null DEFAULT '' comment '看球名称',
  `city_id` bigint comment '城市ID',
  `avater` varchar(200) comment '封面ID',
  `description` varchar(2000) DEFAULT '' comment '介绍',

  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='直播看球表';


DROP TABLE IF EXISTS `t_big_race`;

/** 大型赛事表 **/
CREATE TABLE `t_big_race` (
  `id` bigint(32) NOT NULL auto_increment,
  `name` varchar(50) not null DEFAULT '' comment '看球名称',
  `team1name` varchar(50) DEFAULT '' comment '球队1名称',
  `avater1` varchar(200) DEFAULT '' comment '球队1队徽',
  `team2name` varchar(50) DEFAULT '' comment '球队2名称',
  `avater2` varchar(200) DEFAULT '' comment '球队2队徽',
  `start_date` bigint comment '比赛时间',
  `description` varchar(2000) DEFAULT '' comment '介绍',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='大型赛事表';

DROP TABLE IF EXISTS `t_post`;

/** 帖子表 **/
CREATE TABLE `t_post` (
  `id` bigint(32) NOT NULL auto_increment,
  `user_id` bigint not null comment '发布人ID',
  `content` varchar(2000) comment '帖子内容',
  `share_count` int(2) DEFAULT 0 comment '分享次数',
  `report_count` int(2) DEFAULT 0 comment '举报次数',
  `status` int(2) DEFAULT 0 comment '帖子状态0:正常 1:冻结', 
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='帖子表 ';

DROP TABLE IF EXISTS `t_post_image`;

/** 帖子图片表 **/
CREATE TABLE `t_post_image` (
  `id` bigint(32) NOT NULL auto_increment,
  `post_id` bigint not null comment '帖子ID',
  `avater` varchar(200) comment '图片url',
  `status` int(2) DEFAULT 0 comment '帖子状态0:正常 1:冻结', 
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='帖子图片表';

DROP TABLE IF EXISTS `t_post_comment`;

/** 帖子评论表 **/
CREATE TABLE `t_post_comment` (
  `id` bigint(32) NOT NULL auto_increment,
  `post_id` bigint not null comment '帖子ID',
  `from_user_id` bigint comment '发表评论ID',
  `to_user_id` bigint comment '被评论人ID,为空则直接回复帖子',
  `status` int(2) DEFAULT 0 comment '帖子状态0:正常 1:冻结', 
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='帖子评论表';


DROP TABLE IF EXISTS `t_activity`;

/** 活动表 **/
CREATE TABLE `t_activity` (
  `id` bigint(32) NOT NULL auto_increment,
  `title` bigint not null comment '活动标题',
  `avater` varchar(200) comment '封面URL',
  `introduction` varchar(2000) DEFAULT '' comment '简介',
  `description` varchar(2000) DEFAULT '' comment '介绍',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动表';

DROP TABLE IF EXISTS `t_information`;

/** 资讯表 **/
CREATE TABLE `t_information` (
  `id` bigint(32) NOT NULL auto_increment,
  `title` bigint not null comment '活动标题',
  `avater` varchar(200) comment '封面URL',
  `type` int(2) DEFAULT 0 comment '类型 0:纯文本 1:网页',
  `introduction` varchar(2000) DEFAULT '' comment '简介',
  `description` varchar(2000) DEFAULT '' comment '介绍',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='咨询表';

DROP TABLE IF EXISTS `t_system_explain`;

/** 系统说明表 **/
CREATE TABLE `t_system_explain` (
  `id` bigint(32) NOT NULL auto_increment,
  `title` varchar(50) DEFAULT '' comment '标题',
  `type` int(2) DEFAULT 0 comment '类型 0:vip等级说明 1:vip优惠说明 2:信誉度说明 3:约球须知 4:付款说明 5:保险说明',
  `description` varchar(2000) DEFAULT '' comment '介绍',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统说明表';

DROP TABLE IF EXISTS `t_system_banner`;

/** 系统APPbanner图表 **/
CREATE TABLE `t_system_banner` (
  `id` bigint(32) NOT NULL auto_increment,
  `avater` varchar(200) DEFAULT '' comment '图片URL',
  `type` int(2) DEFAULT 0 comment '0:活动 1:资讯 2:大型赛事',
  `to_id` bigint(32) not null comment '跳转ID',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统APPbanner图表';

DROP TABLE IF EXISTS `t_system_vip`;

/** 系统vip设置表 **/
CREATE TABLE `t_system_vip` (
  `id` bigint(32) NOT NULL auto_increment,
  `price` double DEFAULT 0 comment '价格/年',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统vip设置表';

DROP TABLE IF EXISTS `t_system_vip_level`;

/** 系统vip等级设置表 **/
CREATE TABLE `t_system_vip_level` (
  `id` bigint(32) NOT NULL auto_increment,
  `system_vip_id` bigint DEFAULT 0 comment 'vip ID',
  `level` int(2) DEFAULT 1 comment '等级',
  `experience` int(11) DEFAULT 0 comment '需要经验',
  `preferente` double DEFAULT 0 comment '优惠，这里主要是打折值',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统vip等级设置表';

DROP TABLE IF EXISTS `t_system_vip_experience`;

/** 系统vip经验设置表 **/
CREATE TABLE `t_system_vip_experience` (
  `id` bigint(32) NOT NULL auto_increment,
  `system_vip_id` bigint DEFAULT 0 comment 'vip ID',
  `experience` int(2) DEFAULT 1 comment '经验',
  `action` varchar(50) DEFAULT '' comment '获得经验动作',
  `code` varchar(20) DEFAULT '' comment '编码',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统vip经验设置表';

DROP TABLE IF EXISTS `t_system_vip_credibility`;

/** 系统vip信誉度设置表 **/
CREATE TABLE `t_system_vip_credibility` (
  `id` bigint(32) NOT NULL auto_increment,
  `system_vip_id` bigint DEFAULT 0 comment 'vip ID',
  `credibility` int(2) DEFAULT 1 comment '经验',
  `action` varchar(50) DEFAULT '' comment '获得经验动作',
  `code` varchar(20) DEFAULT '' comment '编码',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统vip信誉度设置表';

DROP TABLE IF EXISTS `t_system_message`;

/** 系统消息表 **/
CREATE TABLE `t_system_message` (
  `id` bigint(32) NOT NULL auto_increment,
  `to_user_id` bigint not null comment '接收用户ID',
  `title` varchar(50) DEFAULT '' comment '系统标题',
  `content` varchar(500) DEFAULT '' comment '消息内容',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统消息表';

DROP TABLE IF EXISTS `t_system_report`;

/** 用户反馈表 **/
CREATE TABLE `t_system_report` (
  `id` bigint(32) NOT NULL auto_increment,
  `user_id` bigint not null comment '接收用户ID',
  `mobile` varchar(50) DEFAULT '' comment '联系方式',
  `content` varchar(500) DEFAULT '' comment '消息内容',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户反馈表';


DROP TABLE IF EXISTS `t_system_insurance`;

/** 保险表 **/
CREATE TABLE `t_system_insurance` (
  `id` bigint(32) NOT NULL auto_increment,
  `name` varchar(50) DEFAULT '' comment '保险名称',
  `price` double DEFAULT 0 comment '单价',
  `bj` double DEFAULT 0 comment '事故保金',
  `company` varchar(50) DEFAULT '' comment '保险公司名称',
  `mobile` varchar(50) DEFAULT '' comment '联系方式',
  `status` int(2) DEFAULT 0 comment '状态 0:有效 1:失效',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保险表';

DROP TABLE IF EXISTS `t_accident`;

/** 事故表 **/
CREATE TABLE `t_accident` (
  `id` bigint(32) NOT NULL auto_increment,
  `race_id` varchar(50) DEFAULT '' comment '约球ID',
  `status` int(2) DEFAULT 0 comment '状态 0:未处理 1:已处理',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='事故表';

DROP TABLE IF EXISTS `t_log`;

/** 日志表 **/
CREATE TABLE `t_log` (
  `id` bigint(32) NOT NULL auto_increment,
  `message` varchar(100) DEFAULT '' comment '日志消息',
  `user_id` bigint COMMENT '操作用户ID',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志表';

DROP TABLE IF EXISTS `t_module_relation`;

/** 模块关联表 **/
CREATE TABLE `t_module_relation` (
  `id` bigint(32) NOT NULL auto_increment,
  `parent_id` bigint not null,
  `child_id` bigint not null,
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模块关联表';