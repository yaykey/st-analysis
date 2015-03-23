/*
 * 文件名： PreviewUtil.java

 * 
 * 创建日期： 2013-9-13
 *
 * Copyright(C) 2013, by lilinbing.
 *
 * 原始作者:lilinbing
 *
 */
package com.st.framework.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 预览图片
 * @author lilinbing
 *
 */
public class PreviewUtil {
	private File file;
	private String fileType;
	private String filename;

	public PreviewUtil(File file,String fileType,String filename){
		this.file = file;
		this.fileType = fileType;
		this.filename = filename;
	}
	public PreviewUtil(String filename){
		this.filename = filename;
	}
	private String sessionkey(){
		return  UUID.randomUUID().toString();  
	}
	/**
	 * 放入图片到session
	 * @param request
	 * @return
	 */
	public Map<String, Object> uploadFile(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		if(fileType.equals("image/gif") || fileType.equals("image/jpeg") ||   
				fileType.equals("image/png") || fileType.equals("image/bmp") ||   
				fileType.equals("image/x-icon") || fileType.equals("image/pjpeg"))  {
			String sessionkey = sessionkey();
            try {  
                request.getSession().setAttribute(sessionkey, new FileInputStream(file));
                map.put("filename", sessionkey);
                map.put("chk", "0"); //上传成功
            } catch (IOException e) {  
            	 map.put("filename", "");
            	 map.put("chk", "2");//上传失败
                e.printStackTrace();  
            }   
		}else{
			 map.put("chk", "1");//文件类型不对
		}
		return map;
	}
	
	/**
	 * 显示预览图片
	 * @param request
	 * @param response
	 */
	public void showImg(HttpServletRequest request,HttpServletResponse response){
		OutputStream output = null;
		InputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			
			bis = (InputStream)request.getSession().getAttribute(filename);
			if(bis==null)
				return ;
			output = response.getOutputStream();
			bos=new BufferedOutputStream(output);
			byte data[]=new byte[4096];//缓冲字节数
			int size=0; 
			size=bis.read(data);
			while (size!=-1){
				bos.write(data,0,size);
				size=bis.read(data);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(bis!=null){
					request.getSession().removeAttribute(filename);
					bis.close();
				}
				if(output!=null){
					output.close();
				}
				if(bos!=null){
					bos.flush();//清空输出缓冲流
					bos.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}
	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}
	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}
	/**
	 * @param fileType the fileType to set
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
}
