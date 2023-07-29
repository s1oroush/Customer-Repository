create table tb_cst_individual_customer
(
    customer_id         number(12) not null,
    citizenship_type    varchar2(20),
    national_id         varchar2(10),
    identification_code varchar2(50),
    first_name          varchar2(200),
    last_name           varchar2(200),
    father_name         varchar2(200),
    transaction_id      varchar2(100) not null,
    created_by          varchar2(100) not null,
    created_date        timestamp(6) not null,
    last_modified_by    varchar2(100) not null,
    last_modified_date  timestamp(6) not null,
    revision_comment    varchar2(1000),
    revision_number     number(12)
)
;

-- Add comments to the table
comment
on table tb_cst_individual_customer
  is 'جدول مشخصات مشترک حقيقي';

-- Add comments to the columns
comment
on column tb_cst_individual_customer.customer_id
  is 'شناسه مشترک'
comment on column tb_cst_individual_customer.citizenship_type
  is 'نوع تابعيت مشترک';
comment
on column tb_cst_individual_customer.national_id
  is 'شناسه ملی مشترک'
comment on column tb_cst_individual_customer.identification_code
  is 'کد شناسايي مشترک';
comment
on column tb_cst_individual_customer.first_name
  is 'نام';
comment
on column tb_cst_individual_customer.last_name
  is 'نام خانوادگي';
comment
on column tb_cst_individual_customer.father_name
  is 'نام پدر';
comment
on column tb_cst_individual_customer.transaction_id
  is 'شناسه تراکنش. این شناسه از سیستم جهت ارتباط با سایر موجودیتها تکمیل می شود';
comment
on column tb_cst_individual_customer.created_by
  is 'ایجاد کننده رکورد';
comment
on column tb_cst_individual_customer.created_date
  is 'تاریخ ایجاد رکورد';
comment
on column tb_cst_individual_customer.last_modified_by
  is 'آخرین ویرایش کننده رکورد';
comment
on column tb_cst_individual_customer.last_modified_date
  is 'تاریخ آخرین ویرایش رکورد';
comment
on column tb_cst_individual_customer.revision_comment
  is 'توضيح نسخه جاري از رکورد';
comment
on column tb_cst_individual_customer.revision_number
  is 'شماره ویرایش رکورد';

-- Create/Recreate primary, unique and foreign key constraints
alter table tb_cst_individual_customer
    add constraint pk_cst_ind_cst_id primary key (CUSTOMER_ID);
alter table tb_cst_individual_customer
    add constraint ix_cst_ind_cst_cttp_id unique (NATIONAL_ID);

-- Create/Recreate check constraints
alter table tb_cst_individual_customer
    add constraint CK_CST_IND_CST_CT_TYP
        check (citizenship_type in ('IRANIAN','REFUGEE','FOREIGNERS','AMAYESH'));

-- Create sequence
create sequence sq_cst_customer
    minvalue 1
    maxvalue 999999999999
    start with 1
    increment by 1
    cache 2
cycle
order;

-- ++++++++++++++++++++++++++++++ tb_cst_corporation_customer ++++++++++++++++++++++++++++++++++++
create table tb_cst_corporation_customer
(
    customer_id            number(12) not null,
    customer_type          VARCHAR2(20),
    national_id            varchar2(100),
    customer_name          varchar2(200),
    registration_date      timestamp(6),
    registration_number    varchar2(200),
    transaction_id         varchar2(100) not null,
    created_by             varchar2(100) not null,
    created_date           timestamp(6) not null,
    last_modified_by       varchar2(100) not null,
    last_modified_date     timestamp(6) not null,
    revision_comment       varchar2(1000),
    revision_number        number(12)
)
;

-- Add comments to the table
comment on table tb_cst_corporation_customer
  is 'جدول مشخصات مشترک حقوقي';

-- Add comments to the columns
comment on column tb_cst_corporation_customer.customer_id
  is 'شناسه مشترک';
comment on column tb_cst_corporation_customer.customer_type
  is 'نوع مشترک حقوقي، دولتي يا خصوصي';
comment on column tb_cst_corporation_customer.national_id
  is 'شناسه ملي مشترک';
comment on column tb_cst_corporation_customer.customer_name
  is 'نام مشترک';
comment on column tb_cst_corporation_customer.registration_date
  is 'تاريخ ثبت شرکت';
comment on column tb_cst_corporation_customer.registration_number
  is 'شماره ثبت شرکت';
comment on column tb_cst_corporation_customer.transaction_id
  is 'شناسه تراکنش. این شناسه از سیستم جهت ارتباط با سایر موجودیتها تکمیل می شود';
comment on column tb_cst_corporation_customer.created_by
  is 'ایجاد کننده رکورد';
comment on column tb_cst_corporation_customer.created_date
  is 'تاریخ ایجاد رکورد';
comment on column tb_cst_corporation_customer.last_modified_by
  is 'آخرین ویرایش کننده رکورد';
comment on column tb_cst_corporation_customer.last_modified_date
  is 'تاریخ آخرین ویرایش رکورد';
comment on column tb_cst_corporation_customer.revision_comment
  is 'توضيح نسخه جاري از رکورد';
comment on column tb_cst_corporation_customer.revision_number
  is 'شماره ویرایش رکورد';

-- Create/Recreate primary, unique and foreign key constraints
alter table tb_cst_corporation_customer
    add constraint pk_cst_corp_cst_id primary key (CUSTOMER_ID);
alter table tb_cst_corporation_customer
    add constraint ix_cst_corp_cst_cttp_id unique (NATIONAL_ID);

-- Create/Recreate check constraints
alter table TB_CST_CORPORATION_customer
    add constraint CK_CST_CORP_TYPE
        check (customer_type in ('gov','co'));
