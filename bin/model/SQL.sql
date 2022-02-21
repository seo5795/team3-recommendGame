
--[ 테이블 생성 ]

--회원테이블
create table userdb(
	unum int primary key, --회원번호
	id varchar(30) not null, --회원아이디
	pw varchar(40) --회원패스워드
);

--게임테이블
create table game(
	gnum int primary key, --게임번호
	gname varchar(80), --게임명
	genre varchar(20), --게임장르
	price int default 0,  --가격
	discount int default 0, --할인율
	dcnt int default 0, --다운횟수
	rcnt int default 0, --추천횟수
	gdate varchar(40) --출시일자
);



--게임다운 리스트 테이블
create table gamedown(
    dnum int primary key, --게임다운번호
    unum int not null, --회원번호
    constraint fk_unum foreign key(unum) references userdb(unum) on delete cascade,
    gnum int not null, --게임번호
    constraint fk_gnum foreign key(gnum) references game(gnum),
    reyn char(1) not null --추천여부 1이 true, 0이 false
);

--회원정보 입력
insert into userdb values(1, 'admin','1234');--관리자 계정생성
insert into userdb values(1000, 'client', '3434ncnc');
insert into userdb values((select nvl(max(unum),0)+1 from userdb),'nullco','1234');
insert into userdb values((select nvl(max(unum),0)+1 from userdb),'gamelover12','1234');



-- 테이블 삭제
drop table gamedown;
drop table game;
drop table userdb;

--테이블 선택
select * from game;
select * from gamedown;
select * from USERDB;

-- 기준별 리스트화
select * from game order by discount desc;
select * from game order by genre;
select * from game order by gdate;
select * from game order by rcnt desc;
select * from game order by price;

-- 데이터 수정(장르추가)
update game set genre='Action' where gnum=1;
update game set genre='Racing' where gnum=2;
update game set genre='Casual' where gnum=3;
update game set genre='Casual' where gnum=4;
update game set genre='Racing' where gnum=5;
update game set genre='Casual' where gnum=6;
update game set genre='Simulation' where gnum=7;
update game set genre='Action' where gnum=8;
update game set genre='RPG' where gnum=9;
update game set genre='Simulation' where gnum=10;
update game set genre='Adventure' where gnum=11;
update game set genre='RPG' where gnum=12;
update game set genre='Casual' where gnum=13;
update game set genre='Casual' where gnum=14;
update game set genre='Simulation' where gnum=15;
update game set genre='RPG' where gnum=16;
update game set genre='Action' where gnum=17;
update game set genre='Action' where gnum=18;
update game set genre='Action' where gnum=19;
update game set genre='Action' where gnum=20;
update game set genre='Simulation' where gnum=21;
update game set genre='Simulation' where gnum=22;
update game set genre='Simulation' where gnum=23;
update game set genre='Simulation' where gnum=24;
update game set genre='Simulation' where gnum=25;
update game set genre='Simulation' where gnum=26;
update game set genre='RPG' where gnum=27;
update game set genre='RPG' where gnum=28;
update game set genre='RPG' where gnum=29;
update game set genre='RPG' where gnum=30;
update game set genre='Novel' where gnum=31;
update game set genre='Simulation' where gnum=32;
update game set genre='Action' where gnum=33;
update game set genre='RPG' where gnum=34;
update game set genre='Casual' where gnum=35;
update game set genre='Simulation' where gnum=36;
update game set genre='Simulation' where gnum=37;
update game set genre='Action' where gnum=38;
update game set genre='Simulation' where gnum=39;
update game set genre='Action' where gnum=40;
update game set genre='Simulation' where gnum=41;
update game set genre='Simulation' where gnum=42;
update game set genre='Simulation' where gnum=43;
update game set genre='Simulation' where gnum=44;
update game set genre='RPG' where gnum=45;
update game set genre='Adventure' where gnum=46;
update game set genre='Casual' where gnum=47;
update game set genre='Casual' where gnum=48;
update game set genre='Casual' where gnum=49;
update game set genre='Action' where gnum=50;
-- 연습용
insert into game values((select nvl(max(gnum),0)+1 from game),'Game','Racing',10000,10,1,1,'null');
update game set gdate=TO_CHAR(SYSDATE, 'D MON, YYYY') where gnum=51;
select * from game;