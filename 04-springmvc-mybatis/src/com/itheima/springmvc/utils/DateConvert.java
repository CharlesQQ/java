package com.itheima.springmvc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 日期转换器
 * @author changqiangqiang
 *	S: source 要转换为源类型
 *  T: 目标, 要转换成的数据类型
 */
public class DateConvert implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		Date result = null;
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			result = sdf.parse(source);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
