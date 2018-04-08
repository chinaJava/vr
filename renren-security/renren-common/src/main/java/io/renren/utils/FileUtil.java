
package io.renren.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class FileUtil
{

	/**
	 * @function: 将输入流写成文件
	 * @param path 文件路径和文件名
	 * @param is 输入流
	 */
	public static void write(String path, InputStream is)
	{
		File f = createFileIfNotExist(path);
		byte[] buffer = new byte[8192];
		OutputStream fos = null;
		int readByte = 0;
		try
		{
			fos = new FileOutputStream(f);
			while ((readByte = is.read(buffer, 0, 8192)) != -1)
			{
				fos.write(buffer, 0, readByte);
			}
			fos.flush();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (is != null)
				{
					is.close();
					is = null;
				}
				if (fos != null)
				{
					fos.close();
					fos = null;
				}
				if (f != null)
				{
					f = null;
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static File createFileIfNotExist(String path)
	{
		String[] filePathArray = path.split("\\\\|/");
		StringBuilder sbPath = new StringBuilder();
		File temp = null;
		int level = filePathArray.length;
		int cur = 0;
		for (String frag : filePathArray)
		{
			cur++;
			sbPath.append(frag).append(File.separator);
			temp = new File(sbPath.toString());
			if (!temp.exists())
			{
				if (cur < level)
				{
					temp.mkdir();
				} else
				{
					try
					{
						temp.createNewFile();
					} catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		return temp;
	}
}
