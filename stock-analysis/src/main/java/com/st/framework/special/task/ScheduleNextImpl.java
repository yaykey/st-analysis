package com.st.framework.special.task;

import java.util.Calendar;

import java.util.Date;

import com.st.framework.special.scheduler.SchedulerNext;

/**
 * ScheduleNextImpl实现类
 *
 * <p>类名：ScheduleNextImpl.java</p>
 */
public class ScheduleNextImpl implements SchedulerNext
{
	
	

	private Date nextDate = null;

	private String seperate = null;

	private int maxCount = 0;

	private int nextCount = 0;

	private int times = 1;

	public ScheduleNextImpl(Date begin, String sep, int count) {

		nextDate = begin;
		seperate = sep;
		maxCount = count;
	}

	public ScheduleNextImpl(Date begin, String sep, int count, int times) {

		nextDate = begin;
		seperate = sep;
		maxCount = count;
		this.times = times;
	}

	public Date next()
	{

		nextCount++;
		if (nextCount <= maxCount || maxCount < 0)
		{
			Calendar calendar = Calendar.getInstance();
			do
			{
				calendar.setTime(nextDate);
				if (seperate.equals(SchedulerNext.PER_YEAR))
					calendar.add(Calendar.YEAR, times);
				else if (seperate.equals(SchedulerNext.PER_MONTH))
					calendar.add(Calendar.MONTH, times);
				else if (seperate.equals(SchedulerNext.PER_WEEK))
					calendar.add(Calendar.WEEK_OF_YEAR, times);
				else if (seperate.equals(SchedulerNext.PER_DAY))
					calendar.add(Calendar.DATE, times);
				else if (seperate.equals(SchedulerNext.PER_HOUR))
					calendar.add(Calendar.HOUR_OF_DAY, times);
				else if (seperate.equals(SchedulerNext.PER_MINUTE))
					calendar.add(Calendar.MINUTE, times);
				else if (seperate.equals(SchedulerNext.PER_HALF_MINUTE))
					calendar.add(Calendar.MINUTE, 30);
//									calendar.add(Calendar.SECOND , 30);
				else
					calendar.add(Calendar.SECOND, times);
				nextDate = calendar.getTime();
				calendar = Calendar.getInstance();
			} while (nextDate.before(calendar.getTime()));
			return nextDate;
		} else
			return null;
	}

	public static void main(String[] args)
	{

		try
		{
			Date d = Calendar.getInstance().getTime();
			ScheduleNextImpl sn = new ScheduleNextImpl(d,
					SchedulerNext.PER_SECOND, 1000);
			while (true)
			{
				d = sn.next();
				if (d == null)
					break;
				System.out.println((d));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
