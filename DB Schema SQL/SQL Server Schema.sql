/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2012                    */
/* Created on:     2017/10/16 AM 12:50:52                       */
/*==============================================================*/


if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('TB_DETAIL') and o.name = 'FK_001')
alter table TB_DETAIL
   drop constraint FK_001
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('TB_MANY_REL') and o.name = 'FK_002')
alter table TB_MANY_REL
   drop constraint FK_002
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('TB_MANY_REL') and o.name = 'FK_003')
alter table TB_MANY_REL
   drop constraint FK_003
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TB_DETAIL')
            and   type = 'U')
   drop table TB_DETAIL
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TB_MANY1')
            and   type = 'U')
   drop table TB_MANY1
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TB_MANY2')
            and   type = 'U')
   drop table TB_MANY2
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TB_MANY_REL')
            and   type = 'U')
   drop table TB_MANY_REL
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TB_MASTER')
            and   type = 'U')
   drop table TB_MASTER
go

/*==============================================================*/
/* Table: TB_DETAIL                                             */
/*==============================================================*/
create table TB_DETAIL (
   ID                   varchar(36)          not null,
   TB_MASTER_ID         varchar(36)          null,
   NAME                 varchar(30)          null,
   CODE                 varchar(15)          null,
   constraint PK_TB_DETAIL primary key nonclustered (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('TB_DETAIL') and minor_id = 0)
begin 
   declare @CurrentUser sysname 
select @CurrentUser = user_name() 
execute sp_dropextendedproperty 'MS_Description',  
   'user', @CurrentUser, 'table', 'TB_DETAIL' 
 
end 


select @CurrentUser = user_name() 
execute sp_addextendedproperty 'MS_Description',  
   'Detail Table', 
   'user', @CurrentUser, 'table', 'TB_DETAIL'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_DETAIL')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_DETAIL', 'column', 'ID'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'ID',
   'user', @CurrentUser, 'table', 'TB_DETAIL', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_DETAIL')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TB_MASTER_ID')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_DETAIL', 'column', 'TB_MASTER_ID'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Master table ID',
   'user', @CurrentUser, 'table', 'TB_DETAIL', 'column', 'TB_MASTER_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_DETAIL')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_DETAIL', 'column', 'NAME'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Name',
   'user', @CurrentUser, 'table', 'TB_DETAIL', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_DETAIL')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CODE')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_DETAIL', 'column', 'CODE'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Code',
   'user', @CurrentUser, 'table', 'TB_DETAIL', 'column', 'CODE'
go

/*==============================================================*/
/* Table: TB_MANY1                                              */
/*==============================================================*/
create table TB_MANY1 (
   ID                   varchar(36)          not null,
   NAME                 varchar(30)          null,
   CODE                 varchar(15)          null,
   constraint PK_TB_MANY1 primary key nonclustered (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('TB_MANY1') and minor_id = 0)
begin 
   declare @CurrentUser sysname 
select @CurrentUser = user_name() 
execute sp_dropextendedproperty 'MS_Description',  
   'user', @CurrentUser, 'table', 'TB_MANY1' 
 
end 


select @CurrentUser = user_name() 
execute sp_addextendedproperty 'MS_Description',  
   'Many1 Table', 
   'user', @CurrentUser, 'table', 'TB_MANY1'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_MANY1')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_MANY1', 'column', 'ID'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'ID',
   'user', @CurrentUser, 'table', 'TB_MANY1', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_MANY1')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_MANY1', 'column', 'NAME'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Name',
   'user', @CurrentUser, 'table', 'TB_MANY1', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_MANY1')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CODE')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_MANY1', 'column', 'CODE'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Code',
   'user', @CurrentUser, 'table', 'TB_MANY1', 'column', 'CODE'
go

/*==============================================================*/
/* Table: TB_MANY2                                              */
/*==============================================================*/
create table TB_MANY2 (
   ID                   varchar(36)          not null,
   NAME                 varchar(30)          null,
   CODE                 varchar(15)          null,
   constraint PK_TB_MANY2 primary key nonclustered (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('TB_MANY2') and minor_id = 0)
begin 
   declare @CurrentUser sysname 
select @CurrentUser = user_name() 
execute sp_dropextendedproperty 'MS_Description',  
   'user', @CurrentUser, 'table', 'TB_MANY2' 
 
end 


select @CurrentUser = user_name() 
execute sp_addextendedproperty 'MS_Description',  
   'Many2 Table', 
   'user', @CurrentUser, 'table', 'TB_MANY2'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_MANY2')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_MANY2', 'column', 'ID'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'ID',
   'user', @CurrentUser, 'table', 'TB_MANY2', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_MANY2')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_MANY2', 'column', 'NAME'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Name',
   'user', @CurrentUser, 'table', 'TB_MANY2', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_MANY2')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CODE')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_MANY2', 'column', 'CODE'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Code',
   'user', @CurrentUser, 'table', 'TB_MANY2', 'column', 'CODE'
go

/*==============================================================*/
/* Table: TB_MANY_REL                                           */
/*==============================================================*/
create table TB_MANY_REL (
   TB_MANY1_ID          varchar(36)          not null,
   TB_MANY2_ID          varchar(36)          not null,
   constraint PK_TB_MANY_REL primary key nonclustered (TB_MANY1_ID, TB_MANY2_ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('TB_MANY_REL') and minor_id = 0)
begin 
   declare @CurrentUser sysname 
select @CurrentUser = user_name() 
execute sp_dropextendedproperty 'MS_Description',  
   'user', @CurrentUser, 'table', 'TB_MANY_REL' 
 
end 


select @CurrentUser = user_name() 
execute sp_addextendedproperty 'MS_Description',  
   'Many1 and Many2 relation table', 
   'user', @CurrentUser, 'table', 'TB_MANY_REL'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_MANY_REL')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TB_MANY1_ID')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_MANY_REL', 'column', 'TB_MANY1_ID'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Many1 table ID',
   'user', @CurrentUser, 'table', 'TB_MANY_REL', 'column', 'TB_MANY1_ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_MANY_REL')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'TB_MANY2_ID')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_MANY_REL', 'column', 'TB_MANY2_ID'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Many2 table ID',
   'user', @CurrentUser, 'table', 'TB_MANY_REL', 'column', 'TB_MANY2_ID'
go

/*==============================================================*/
/* Table: TB_MASTER                                             */
/*==============================================================*/
create table TB_MASTER (
   ID                   varchar(36)          not null,
   NAME                 varchar(30)          null,
   CODE                 varchar(15)          null,
   constraint PK_TB_MASTER primary key nonclustered (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('TB_MASTER') and minor_id = 0)
begin 
   declare @CurrentUser sysname 
select @CurrentUser = user_name() 
execute sp_dropextendedproperty 'MS_Description',  
   'user', @CurrentUser, 'table', 'TB_MASTER' 
 
end 


select @CurrentUser = user_name() 
execute sp_addextendedproperty 'MS_Description',  
   'Master Table', 
   'user', @CurrentUser, 'table', 'TB_MASTER'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_MASTER')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_MASTER', 'column', 'ID'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'ID',
   'user', @CurrentUser, 'table', 'TB_MASTER', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_MASTER')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'NAME')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_MASTER', 'column', 'NAME'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Name',
   'user', @CurrentUser, 'table', 'TB_MASTER', 'column', 'NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('TB_MASTER')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'CODE')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'TB_MASTER', 'column', 'CODE'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Code',
   'user', @CurrentUser, 'table', 'TB_MASTER', 'column', 'CODE'
go

alter table TB_DETAIL
   add constraint FK_001 foreign key (TB_MASTER_ID)
      references TB_MASTER (ID)
go

alter table TB_MANY_REL
   add constraint FK_002 foreign key (TB_MANY1_ID)
      references TB_MANY1 (ID)
go

alter table TB_MANY_REL
   add constraint FK_003 foreign key (TB_MANY2_ID)
      references TB_MANY2 (ID)
go

