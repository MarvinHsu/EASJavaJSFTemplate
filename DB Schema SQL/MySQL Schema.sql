/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/10/9 PM 05:29:45                        */
/*==============================================================*/


drop table if exists TB_DETAIL;

drop table if exists TB_MANY1;

drop table if exists TB_MANY2;

drop table if exists TB_MANY_REL;

drop table if exists TB_MASTER;

/*==============================================================*/
/* Table: TB_DETAIL                                             */
/*==============================================================*/
create table TB_DETAIL
(
   ID                   varchar(36) not null comment 'ID',
   TB_MASTER_ID         varchar(36) comment 'Master table ID',
   NAME                 varchar(30) comment 'Name',
   CODE                 varchar(15) comment 'Code',
   primary key (ID)
);

alter table TB_DETAIL comment 'Detail Table';

/*==============================================================*/
/* Table: TB_MANY1                                              */
/*==============================================================*/
create table TB_MANY1
(
   ID                   varchar(36) not null comment 'ID',
   NAME                 varchar(30) comment 'Name',
   CODE                 varchar(15) comment 'Code',
   primary key (ID)
);

alter table TB_MANY1 comment 'Many1 Table';

/*==============================================================*/
/* Table: TB_MANY2                                              */
/*==============================================================*/
create table TB_MANY2
(
   ID                   varchar(36) not null comment 'ID',
   NAME                 varchar(30) comment 'Name',
   CODE                 varchar(15) comment 'Code',
   primary key (ID)
);

alter table TB_MANY2 comment 'Many2 Table';

/*==============================================================*/
/* Table: TB_MANY_REL                                           */
/*==============================================================*/
create table TB_MANY_REL
(
   TB_MANY1_ID          varchar(36) not null comment 'Many1 table ID',
   TB_MANY2_ID          varchar(36) not null comment 'Many2 table ID',
   primary key (TB_MANY1_ID, TB_MANY2_ID)
);

alter table TB_MANY_REL comment 'Many1 and Many2 relation table';

/*==============================================================*/
/* Table: TB_MASTER                                             */
/*==============================================================*/
create table TB_MASTER
(
   ID                   varchar(36) not null comment 'ID',
   NAME                 varchar(30) comment 'Name',
   CODE                 varchar(15) comment 'Code',
   primary key (ID)
);

alter table TB_MASTER comment 'Master Table';

alter table TB_DETAIL add constraint FK_001 foreign key (TB_MASTER_ID)
      references TB_MASTER (ID) on delete restrict on update restrict;

alter table TB_MANY_REL add constraint FK_002 foreign key (TB_MANY1_ID)
      references TB_MANY1 (ID) on delete restrict on update restrict;

alter table TB_MANY_REL add constraint FK_003 foreign key (TB_MANY2_ID)
      references TB_MANY2 (ID) on delete restrict on update restrict;

