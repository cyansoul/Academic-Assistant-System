package booking.util;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import booking.exception.CustomeException;

// process files
public class FileUtil {
	private static final String[] exts = { "jpeg", "png", "jpg", "gif", "bmp" };

	/**
	 * process uploaded pictures
	 * @param file
	 * @param parentPath
	 */
	public static String uploadImage(MultipartFile file, String parentPath) {
		if (file != null) {
			try {
				String orginalName = file.getOriginalFilename();
				String[] tempExts = StringUtils.split(orginalName, ".");
				if (!existInExts(tempExts[tempExts.length - 1])) {
					throw new CustomeException("Please upload image!");
				} else {
					String path = parentPath + "/" + file.getOriginalFilename();
					file.transferTo(new File(path));
					return file.getOriginalFilename();
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new CustomeException("cannot upload image, please check it!");
			}
		}
		return null;
	}

	/**
	 * check if it is a picture file
	 * @param ext
	 * @return
	 */
	private static boolean existInExts(String ext) {
		for (String string : exts) {
			if (string.equals(ext)) {
				return true;
			}
		}
		return false;
	}

}
