
drop procedure if  exists `st`.`test_cursor` ;

delimiter //  
create procedure test_cursor (in in_date_id int(10), in in_vol_dim_id int(10), in dropflag boolean, in truncateflag boolean)  
begin

	declare _TIME_DIM_ID int;
	declare _RPT_ID int;
	declare _DATE_ID int;

	declare _TIME_DIMTYPE_ID int;

	declare _BUY_PRICE double(9,3);
	declare _SELL_PRICE double(9,3);
	declare _NEUTER_PRICE double(9,3);
	declare _PRICE double(9,3);

	declare _BUY_PRICE_CHANGES double(9,3);
	declare _SELL_PRICE_CHANGES double(9,3);
	declare _NEUTER_PRICE_CHANGES double(9,3);
	declare _PRICE_CHANGES double(9,3);
	                                           
	declare _BUY_CJBS int;
	declare _SELL_CJBS int;
	declare _NEUTER_CJBS int;
	declare _CJBS int;
	
	declare _BUY_AMO int;
	declare _SELL_AMO int;
	declare _NEUTER_AMO int;
	declare _AMO int;
	                                           
	declare _BUY_VOL int;
	declare _SELL_VOL int;
	declare _NEUTER_VOL int;
	declare _VOL int;
    
	

   	declare done int default 0;
	
   
	declare zeroflag int default 0;
	
    declare cur_test CURSOR FOR  
    (
	select rrr.*
	from (
		select
		r1.TIME_DIM_ID, 
		r1.RPT_ID,r1.DATE_ID,r1.TIME_DIMTYPE_ID,
		
		IFNULL(r1.PRICE,0)  as BUY_PRICE, 
		IFNULL(r2.PRICE,0)  as SELL_PRICE,
		IFNULL(r3.PRICE,0)  as NEUTER_PRICE,

		IFNULL(r1.PRICE_CHANGES,0)  as BUY_PRICE_CHANGES, 
		IFNULL(r2.PRICE_CHANGES,0)  as SELL_PRICE_CHANGES,
		IFNULL(r3.PRICE_CHANGES,0)  as NEUTER_PRICE_CHANGES,

		IFNULL(r1.CJBS,0)  as BUY_CJBS, 
		IFNULL(r2.CJBS,0)  as SELL_CJBS,
		IFNULL(r3.CJBS,0)  as NEUTER_CJBS,

		IFNULL(r1.AMO_VALUE,0)  as BUY_AMO, 
		IFNULL(r2.AMO_VALUE,0)  as SELL_AMO,
		IFNULL(r3.AMO_VALUE,0)  as NEUTER_AMO,
		
		IFNULL(r1.VOL_VALUE,0)  as BUY_VOL, 
		IFNULL(r2.VOL_VALUE,0)  as SELL_VOL,
		IFNULL(r3.VOL_VALUE,0)  as NEUTER_VOL
		from (
		select * 
		from R_RANGE
		where date_id = in_date_id and VOL_DIM_ID=in_vol_dim_id
		and INDEX_ID=3001101
		) r1
		left join  (
		select * from R_RANGE
		where date_id = in_date_id and VOL_DIM_ID=in_vol_dim_id
		and INDEX_ID=3001102
		) r2 
		on r1.TIME_DIM_ID=r2.TIME_DIM_ID
		left join  (
		select * from R_RANGE
		where date_id = in_date_id and VOL_DIM_ID=in_vol_dim_id
		and INDEX_ID=3001103
		) r3
		on r1.TIME_DIM_ID=r3.TIME_DIM_ID
	
	union
		select
		r1.TIME_DIM_ID,
		r1.RPT_ID,r1.DATE_ID,r1.TIME_DIMTYPE_ID,
		
		IFNULL(r1.PRICE,0)  as BUY_PRICE, 
		IFNULL(r2.PRICE,0)  as SELL_PRICE,
		IFNULL(r3.PRICE,0)  as NEUTER_PRICE,

		IFNULL(r1.PRICE_CHANGES,0)  as BUY_PRICE_CHANGES, 
		IFNULL(r2.PRICE_CHANGES,0)  as SELL_PRICE_CHANGES,
		IFNULL(r3.PRICE_CHANGES,0)  as NEUTER_PRICE_CHANGES,

		IFNULL(r1.CJBS,0)  as BUY_CJBS, 
		IFNULL(r2.CJBS,0)  as SELL_CJBS,
		IFNULL(r3.CJBS,0)  as NEUTER_CJBS,
		
		IFNULL(r1.AMO_VALUE,0)  as BUY_AMO, 
		IFNULL(r2.AMO_VALUE,0)  as SELL_AMO,
		IFNULL(r3.AMO_VALUE,0)  as NEUTER_AMO,

		IFNULL(r1.VOL_VALUE,0)  as BUY_VOL, 
		IFNULL(r2.VOL_VALUE,0)  as SELL_VOL,
		IFNULL(r3.VOL_VALUE,0)  as NEUTER_VOL
		from (
		select * 
		from R_RANGE
		where date_id = in_date_id and VOL_DIM_ID=in_vol_dim_id
		and INDEX_ID=3001101
		) r1
		left join  (
		select * from R_RANGE
		where date_id = in_date_id and VOL_DIM_ID=in_vol_dim_id
		and INDEX_ID=3001103
		) r3 
		on r1.TIME_DIM_ID=r3.TIME_DIM_ID
		left join  (
		select * from R_RANGE
		where date_id = in_date_id and VOL_DIM_ID=in_vol_dim_id
		and INDEX_ID=3001102
		) r2
		on r1.TIME_DIM_ID=r2.TIME_DIM_ID
	
	union
	
		select
		r2.TIME_DIM_ID,
		r2.RPT_ID,r2.DATE_ID,r2.TIME_DIMTYPE_ID,
		
				
		IFNULL(r1.PRICE,0)  as BUY_PRICE, 
		IFNULL(r2.PRICE,0)  as SELL_PRICE,
		IFNULL(r3.PRICE,0)  as NEUTER_PRICE,

		IFNULL(r1.PRICE_CHANGES,0)  as BUY_PRICE_CHANGES, 
		IFNULL(r2.PRICE_CHANGES,0)  as SELL_PRICE_CHANGES,
		IFNULL(r3.PRICE_CHANGES,0)  as NEUTER_PRICE_CHANGES,

		IFNULL(r1.CJBS,0)  as BUY_CJBS, 
		IFNULL(r2.CJBS,0)  as SELL_CJBS,
		IFNULL(r3.CJBS,0)  as NEUTER_CJBS,
		
		IFNULL(r1.AMO_VALUE,0)  as BUY_AMO, 
		IFNULL(r2.AMO_VALUE,0)  as SELL_AMO,
		IFNULL(r3.AMO_VALUE,0)  as NEUTER_AMO,

		IFNULL(r1.VOL_VALUE,0)  as BUY_VOL, 
		IFNULL(r2.VOL_VALUE,0)  as SELL_VOL,
		IFNULL(r3.VOL_VALUE,0)  as NEUTER_VOL
		from (
		select * 
		from R_RANGE
		where date_id = in_date_id and VOL_DIM_ID=in_vol_dim_id
		and INDEX_ID=3001102
		) r2
		left join  (
		select * from R_RANGE
		where date_id = in_date_id and VOL_DIM_ID=in_vol_dim_id
		and INDEX_ID=3001101
		) r1 
		on r2.TIME_DIM_ID=r1.TIME_DIM_ID
		left join  (
		select * from R_RANGE
		where date_id = in_date_id and VOL_DIM_ID=in_vol_dim_id
		and INDEX_ID=3001103
		) r3
		on r2.TIME_DIM_ID=r3.TIME_DIM_ID
	
	union
		select
		r2.TIME_DIM_ID,
		r2.RPT_ID,r2.DATE_ID,r2.TIME_DIMTYPE_ID,
				
		IFNULL(r1.PRICE,0)  as BUY_PRICE, 
		IFNULL(r2.PRICE,0)  as SELL_PRICE,
		IFNULL(r3.PRICE,0)  as NEUTER_PRICE,

		IFNULL(r1.PRICE_CHANGES,0)  as BUY_PRICE_CHANGES, 
		IFNULL(r2.PRICE_CHANGES,0)  as SELL_PRICE_CHANGES,
		IFNULL(r3.PRICE_CHANGES,0)  as NEUTER_PRICE_CHANGES,

		IFNULL(r1.CJBS,0)  as BUY_CJBS, 
		IFNULL(r2.CJBS,0)  as SELL_CJBS,
		IFNULL(r3.CJBS,0)  as NEUTER_CJBS,
		
		IFNULL(r1.AMO_VALUE,0)  as BUY_AMO, 
		IFNULL(r2.AMO_VALUE,0)  as SELL_AMO,
		IFNULL(r3.AMO_VALUE,0)  as NEUTER_AMO,
		
		IFNULL(r1.VOL_VALUE,0)  as BUY_VOL, 
		IFNULL(r2.VOL_VALUE,0)  as SELL_VOL,
		IFNULL(r3.VOL_VALUE,0)  as NEUTER_VOL
		from (
		select * 
		from R_RANGE
		where date_id = in_date_id and VOL_DIM_ID=in_vol_dim_id
		and INDEX_ID=3001102
		) r2
		left join  (
		select * from R_RANGE
		where date_id = in_date_id and VOL_DIM_ID=in_vol_dim_id
		and INDEX_ID=3001103
		) r3 
		on r2.TIME_DIM_ID=r3.TIME_DIM_ID
		left join  (
		select * from R_RANGE
		where date_id = in_date_id and VOL_DIM_ID=in_vol_dim_id
		and INDEX_ID=3001101
		) r1
		on r2.TIME_DIM_ID=r1.TIME_DIM_ID
	
	union
	
		select
		r3.TIME_DIM_ID,
		r3.RPT_ID,r3.DATE_ID,r3.TIME_DIMTYPE_ID,
				
		IFNULL(r1.PRICE,0)  as BUY_PRICE, 
		IFNULL(r2.PRICE,0)  as SELL_PRICE,
		IFNULL(r3.PRICE,0)  as NEUTER_PRICE,

		IFNULL(r1.PRICE_CHANGES,0)  as BUY_PRICE_CHANGES, 
		IFNULL(r2.PRICE_CHANGES,0)  as SELL_PRICE_CHANGES,
		IFNULL(r3.PRICE_CHANGES,0)  as NEUTER_PRICE_CHANGES,

		IFNULL(r1.CJBS,0)  as BUY_CJBS, 
		IFNULL(r2.CJBS,0)  as SELL_CJBS,
		IFNULL(r3.CJBS,0)  as NEUTER_CJBS,
		
		IFNULL(r1.AMO_VALUE,0)  as BUY_AMO, 
		IFNULL(r2.AMO_VALUE,0)  as SELL_AMO,
		IFNULL(r3.AMO_VALUE,0)  as NEUTER_AMO,
		
		IFNULL(r1.VOL_VALUE,0)  as BUY_VOL, 
		IFNULL(r2.VOL_VALUE,0)  as SELL_VOL,
		IFNULL(r3.VOL_VALUE,0)  as NEUTER_VOL
		from (
		select * 
		from R_RANGE
		where date_id = in_date_id and VOL_DIM_ID=in_vol_dim_id
		and INDEX_ID=3001103
		) r3
		left join  (
		select * from R_RANGE
		where date_id = in_date_id and VOL_DIM_ID=in_vol_dim_id
		and INDEX_ID=3001101
		) r1 
		on r3.TIME_DIM_ID=r1.TIME_DIM_ID
		left join  (
		select * from R_RANGE
		where date_id = in_date_id and VOL_DIM_ID=in_vol_dim_id
		and INDEX_ID=3001102
		) r2
		on r3.TIME_DIM_ID=r2.TIME_DIM_ID
	
	union
		select
		r3.TIME_DIM_ID,
		r3.RPT_ID,r3.DATE_ID,r3.TIME_DIMTYPE_ID,
				
		IFNULL(r1.PRICE,0)  as BUY_PRICE, 
		IFNULL(r2.PRICE,0)  as SELL_PRICE,
		IFNULL(r3.PRICE,0)  as NEUTER_PRICE,

		IFNULL(r1.PRICE_CHANGES,0)  as BUY_PRICE_CHANGES, 
		IFNULL(r2.PRICE_CHANGES,0)  as SELL_PRICE_CHANGES,
		IFNULL(r3.PRICE_CHANGES,0)  as NEUTER_PRICE_CHANGES,

		IFNULL(r1.CJBS,0)  as BUY_CJBS, 
		IFNULL(r2.CJBS,0)  as SELL_CJBS,
		IFNULL(r3.CJBS,0)  as NEUTER_CJBS,
		
		IFNULL(r1.AMO_VALUE,0)  as BUY_AMO, 
		IFNULL(r2.AMO_VALUE,0)  as SELL_AMO,
		IFNULL(r3.AMO_VALUE,0)  as NEUTER_AMO,
		
		IFNULL(r1.VOL_VALUE,0)  as BUY_VOL, 
		IFNULL(r2.VOL_VALUE,0)  as SELL_VOL,
		IFNULL(r3.VOL_VALUE,0)  as NEUTER_VOL
		from (
		select * 
		from R_RANGE
		where date_id = in_date_id and VOL_DIM_ID=in_vol_dim_id
		and INDEX_ID=3001103
		) r3
		left join  (
		select * from R_RANGE
		where date_id = in_date_id and VOL_DIM_ID=in_vol_dim_id
		and INDEX_ID=3001102
		) r2
		on r3.TIME_DIM_ID=r2.TIME_DIM_ID
		left join  (
		select * from R_RANGE
		where date_id = in_date_id and VOL_DIM_ID=in_vol_dim_id
		and INDEX_ID=3001101
		) r1
		on r3.TIME_DIM_ID=r1.TIME_DIM_ID
	
	) rrr
	order by rrr.TIME_DIM_ID

);

DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;

if dropflag = true then
DROP TABLE IF EXISTS tmpTable;
end if;

create  table if not exists tmpTable
       (
         
	
	RPT_ID int,
	DATE_ID int,
	INDEX_ID int,

	TIME_DIMTYPE_ID int,
	TIME_DIM_ID int,

	VOL_DIMTYPE_ID int,
	VOL_DIM_ID int,
	
	PRICE double(9,3),                                  
	BUY_PRICE double(9,3),
	SELL_PRICE double(9,3),
	NEUTER_PRICE double(9,3),
	
	
	PRICE_CHANGES double(9,3),
	BUY_PRICE_CHANGES double(9,3),
	SELL_PRICE_CHANGES double(9,3),
	NEUTER_PRICE_CHANGES double(9,3),
	
	CJBS int,
	BUY_CJBS int,
	SELL_CJBS int,
	NEUTER_CJBS int,
	
	AMO int,
	BUY_AMO int,
	SELL_AMO int,
	NEUTER_AMO int,
	
	VOL int,
	BUY_VOL int,
	SELL_VOL int,
	NEUTER_VOL int
				
       ) engine=HEAP;
if truncateflag = true then
	truncate TABLE tmpTable;
end if;


	open cur_test;
	REPEAT
		fetch cur_test into 
			_TIME_DIM_ID,
			_RPT_ID,
			_DATE_ID,
			_TIME_DIMTYPE_ID,
			                                 
			_BUY_PRICE,
			_SELL_PRICE,
			_NEUTER_PRICE,
			                                  
			_BUY_PRICE_CHANGES,
			_SELL_PRICE_CHANGES,
			_NEUTER_PRICE_CHANGES,
			                                 
			_BUY_CJBS,
			_SELL_CJBS,
			_NEUTER_CJBS,
			
			_BUY_AMO,
			_SELL_AMO,
			_NEUTER_AMO,
			                                 
			_BUY_VOL,
			_SELL_VOL,
			_NEUTER_VOL;

if _rpt_id is not null then
	set zeroflag=0;
	IF _BUY_PRICE <> 0 THEN  
		set zeroflag=zeroflag + 1;
	END IF;

	IF _SELL_PRICE <> 0 THEN  
		set zeroflag=zeroflag + 1;
	END IF;  

	IF _NEUTER_PRICE <> 0 THEN  
		set zeroflag=zeroflag + 1;
	END IF; 

	set _PRICE=(_BUY_PRICE + _SELL_PRICE + _NEUTER_PRICE)/zeroflag;
	set _PRICE_CHANGES=(_BUY_PRICE_CHANGES + _SELL_PRICE_CHANGES + _NEUTER_PRICE_CHANGES)/zeroflag;
	set _CJBS=(_BUY_CJBS + _SELL_CJBS + _NEUTER_CJBS);
	set _VOL=(_BUY_VOL + _SELL_VOL + _NEUTER_VOL);
	set _AMO=(_BUY_AMO + _SELL_AMO + _NEUTER_AMO);

		insert into tmpTable (
			
			RPT_ID,
			DATE_ID,
			INDEX_ID,
			
			TIME_DIMTYPE_ID,
			TIME_DIM_ID,

			VOL_DIMTYPE_ID,
			VOL_DIM_ID,
			                                    
			BUY_PRICE,
			SELL_PRICE,
			NEUTER_PRICE,
			PRICE,
			                                    
			BUY_PRICE_CHANGES,
			SELL_PRICE_CHANGES,
			NEUTER_PRICE_CHANGES,
			PRICE_CHANGES,
			                                   
			BUY_CJBS,
			SELL_CJBS,
			NEUTER_CJBS,
			CJBS,
			
			BUY_AMO,
			SELL_AMO,
			NEUTER_AMO,
			AMO,
			                                   
			BUY_VOL,
			SELL_VOL,
			NEUTER_VOL,
			VOL
		) 
		values(
			
			_RPT_ID,
			_DATE_ID,
			3001100,

			_TIME_DIMTYPE_ID,
			_TIME_DIM_ID,


			3001,
			IN_VOL_DIM_ID,
			                                 
			_BUY_PRICE,
			_SELL_PRICE,
			_NEUTER_PRICE,
			_PRICE,			

			_BUY_PRICE_CHANGES,
			_SELL_PRICE_CHANGES,
			_NEUTER_PRICE_CHANGES,
			_PRICE_CHANGES,
			                                 
			_BUY_CJBS,
			_SELL_CJBS,
			_NEUTER_CJBS,
			_CJBS,
			
			_BUY_AMO,
			_SELL_AMO,
			_NEUTER_AMO,
			_AMO,
			                                 
			_BUY_VOL,
			_SELL_VOL,
			_NEUTER_VOL,
			_VOL
		);

	set zeroflag=0;
	IF _BUY_PRICE <> 0 THEN  
		set zeroflag=zeroflag + 1;
	END IF;

	IF _SELL_PRICE <> 0 THEN  
		set zeroflag=zeroflag + 1;
	END IF;  

	set _PRICE=(_BUY_PRICE + _SELL_PRICE)/zeroflag;
	set _PRICE_CHANGES=(_BUY_PRICE_CHANGES + _SELL_PRICE_CHANGES)/zeroflag;
	set _CJBS=(_BUY_CJBS + _SELL_CJBS);
	set _VOL=(_BUY_VOL + _SELL_VOL);
	set _AMO=(_BUY_AMO - _SELL_AMO);

	
		insert into tmpTable (

			RPT_ID,
			DATE_ID,
			INDEX_ID,
			
			TIME_DIMTYPE_ID,
			TIME_DIM_ID,

			VOL_DIMTYPE_ID,
			VOL_DIM_ID,

			BUY_PRICE,
			SELL_PRICE,
			NEUTER_PRICE,
			PRICE,
			                                    
			BUY_PRICE_CHANGES,
			SELL_PRICE_CHANGES,
			NEUTER_PRICE_CHANGES,
			PRICE_CHANGES,
			                                   
			BUY_CJBS,
			SELL_CJBS,
			NEUTER_CJBS,
			CJBS,
			
			BUY_AMO,
			SELL_AMO,
			NEUTER_AMO,
			AMO,
			                                   
			BUY_VOL,
			SELL_VOL,
			NEUTER_VOL,
			VOL
		) 
		values(
			
			_RPT_ID,
			_DATE_ID,
			3001104,

			_TIME_DIMTYPE_ID,
			_TIME_DIM_ID,

			3001,
			IN_VOL_DIM_ID,
                                 
			_BUY_PRICE,
			_SELL_PRICE,
			_NEUTER_PRICE,
			_PRICE,			

			_BUY_PRICE_CHANGES,
			_SELL_PRICE_CHANGES,
			_NEUTER_PRICE_CHANGES,
			_PRICE_CHANGES,
			                                 
			_BUY_CJBS,
			_SELL_CJBS,
			_NEUTER_CJBS,
			_CJBS,
			
			_BUY_AMO,
			_SELL_AMO,
			_NEUTER_AMO,
			_AMO,
			                                 
			_BUY_VOL,
			_SELL_VOL,
			_NEUTER_VOL,
			_VOL
		);
end if;
		
	UNTIL done END REPEAT;
	close cur_test;


end;//
DELIMITER ;

call test_cursor(20150525, 300100001, true, true);
call test_cursor(20150525, 300100100, false, false);
call test_cursor(20150525, 300100200, false, false);
call test_cursor(20150525, 300100500, false, false);
call test_cursor(20150525, 300101000, false, false);
call test_cursor(20150525, 300102000, false, false);
call test_cursor(20150525, 300105000, false, false);
call test_cursor(20150525, 300110000, false, false);



select * from tmpTable;


