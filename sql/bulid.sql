create table t_carbon_user
(
    user_id varchar(36) not null primary key ,
    user_type char(1) not null comment '0普通用户1网站管理员',
    email varchar(24) not null comment '登录邮箱',
    nickname varchar(64) not null ,
    create_time datetime default CURRENT_TIMESTAMP null,
    create_by   varchar(255)                       null,
    modify_time datetime                           null,
    modify_by   varchar(255)                       null,
    remark      varchar(1024)                      null
);

create table t_carbon_user_belong
(
    belong_id varchar(36) not null primary key ,
    user_id varchar(36) not null comment '用户id',
    user_nickname varchar(64) not null comment '用户昵称-冗',
    province_id varchar(36) null comment '所在省份',
    province_name varchar(50) null comment '省份名',
    industry varchar(64) null comment '所在行业',
    industry_process varchar(64) null comment '行业环节',
    blur_carbon_output decimal(16, 2) comment '大致年度碳排量',
    create_time datetime default CURRENT_TIMESTAMP null,
    create_by   varchar(255)                       null,
    modify_time datetime                           null,
    modify_by   varchar(255)                       null,
    remark      varchar(1024)                      null
#     foreign key (province_id) references t_provinces(id)
);

create table t_province_carbon_output
(
    output_id varchar(36) not null primary key ,
    province_id varchar(36) not null comment '省份id',
    province_name varchar(50) not null comment '省份名',
    output decimal(16, 2) default 0 comment '碳排量，单位吨',
    year varchar(4) not null comment '统计年份',
    create_time datetime default CURRENT_TIMESTAMP null,
    create_by   varchar(255)                       null,
    modify_time datetime                           null,
    modify_by   varchar(255)                       null,
    remark      varchar(1024)                      null
#     foreign key (province_id) references t_provinces(id)
);

create table t_province_policy
(
    policy_id varchar(36) not null primary key ,
    title varchar(1024) not null ,
    url varchar(1024) not null comment '内容链接',
    province_id varchar(36) not null comment '省份id',
    create_time datetime default CURRENT_TIMESTAMP null,
    create_by   varchar(255)                       null,
    modify_time datetime                           null,
    modify_by   varchar(255)                       null,
    remark      varchar(1024)                      null
) comment '省份双碳政策表';

create table t_province_intent
(
    intent_id varchar(36) not null primary key ,
    title varchar(1024) not null ,
    url varchar(1024) not null comment '内容链接',
    province_id varchar(36) not null comment '省份id',
    create_time datetime default CURRENT_TIMESTAMP null,
    create_by   varchar(255)                       null,
    modify_time datetime                           null,
    modify_by   varchar(255)                       null,
    remark      varchar(1024)                      null
) comment '省份双碳行业政策表';

create table t_province_case
(
    case_id varchar(36) not null primary key ,
    title varchar(1024) not null ,
    url varchar(1024) not null comment '内容链接',
    province_id varchar(36) not null comment '省份id',
    create_time datetime default CURRENT_TIMESTAMP null,
    create_by   varchar(255)                       null,
    modify_time datetime                           null,
    modify_by   varchar(255)                       null,
    remark      varchar(1024)                      null
) comment '省份双碳成功案例表';

create table t_province_news
(
    news_id varchar(36) not null primary key ,
    title varchar(1024) not null ,
    content_url varchar(1024) not null comment '内容链接',
    image_url varchar(1024) not null comment '预览图URL',
    province_id varchar(36) not null comment '省份id',
    create_time datetime default CURRENT_TIMESTAMP null,
    create_by   varchar(255)                       null,
    modify_time datetime                           null,
    modify_by   varchar(255)                       null,
    remark      varchar(1024)                      null
) comment '省份双碳新闻表';