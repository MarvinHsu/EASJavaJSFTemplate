--==============================================================
-- DBMS name:      IBM DB2 UDB 9.7 Common Server
-- Created on:     2017/10/10 ¤U¤È 04:11:48
--==============================================================


alter table TB_DETAIL
   drop foreign key FK_001;

alter table TB_MANY_REL
   drop foreign key FK_002;

alter table TB_MANY_REL
   drop foreign key FK_003;

drop table TB_DETAIL;

drop table TB_MANY1;

drop table TB_MANY2;

drop table TB_MANY_REL;

drop table TB_MASTER;

--==============================================================
-- Table: TB_DETAIL
--==============================================================
create table TB_DETAIL
(
   ID                   VARCHAR(36)            not null,
   TB_MASTER_ID         VARCHAR(36),
   "NAME"               VARCHAR(30),
   CODE                 VARCHAR(15),
   constraint "P_Key_1" primary key (ID)
);

comment on table TB_DETAIL is
'Detail Table';

comment on column TB_DETAIL.ID is
'ID';

comment on column TB_DETAIL.TB_MASTER_ID is
'Master table ID';

comment on column TB_DETAIL."NAME" is
'Name';

comment on column TB_DETAIL.CODE is
'Code';

--==============================================================
-- Table: TB_MANY1
--==============================================================
create table TB_MANY1
(
   ID                   VARCHAR(36)            not null,
   "NAME"               VARCHAR(30),
   CODE                 VARCHAR(15),
   constraint "P_Key_1" primary key (ID)
);

comment on table TB_MANY1 is
'Many1 Table';

comment on column TB_MANY1.ID is
'ID';

comment on column TB_MANY1."NAME" is
'Name';

comment on column TB_MANY1.CODE is
'Code';

--==============================================================
-- Table: TB_MANY2
--==============================================================
create table TB_MANY2
(
   ID                   VARCHAR(36)            not null,
   "NAME"               VARCHAR(30),
   CODE                 VARCHAR(15),
   constraint "P_Key_1" primary key (ID)
);

comment on table TB_MANY2 is
'Many2 Table';

comment on column TB_MANY2.ID is
'ID';

comment on column TB_MANY2."NAME" is
'Name';

comment on column TB_MANY2.CODE is
'Code';

--==============================================================
-- Table: TB_MANY_REL
--==============================================================
create table TB_MANY_REL
(
   TB_MANY1_ID          VARCHAR(36)            not null,
   TB_MANY2_ID          VARCHAR(36)            not null,
   constraint "P_Key_1" primary key (TB_MANY1_ID, TB_MANY2_ID)
);

comment on table TB_MANY_REL is
'Many1 and Many2 relation table';

comment on column TB_MANY_REL.TB_MANY1_ID is
'Many1 table ID';

comment on column TB_MANY_REL.TB_MANY2_ID is
'Many2 table ID';

--==============================================================
-- Table: TB_MASTER
--==============================================================
create table TB_MASTER
(
   ID                   VARCHAR(36)            not null,
   "NAME"               VARCHAR(30),
   CODE                 VARCHAR(15),
   constraint "P_Key_1" primary key (ID)
);

comment on table TB_MASTER is
'Master Table';

comment on column TB_MASTER.ID is
'ID';

comment on column TB_MASTER."NAME" is
'Name';

comment on column TB_MASTER.CODE is
'Code';

alter table TB_DETAIL
   add constraint FK_001 foreign key (TB_MASTER_ID)
      references TB_MASTER (ID)
      on delete restrict on update restrict;

alter table TB_MANY_REL
   add constraint FK_002 foreign key (TB_MANY1_ID)
      references TB_MANY1 (ID)
      on delete restrict on update restrict;

alter table TB_MANY_REL
   add constraint FK_003 foreign key (TB_MANY2_ID)
      references TB_MANY2 (ID)
      on delete restrict on update restrict;

