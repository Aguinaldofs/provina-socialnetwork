package br.com.provina.config.cloudinary;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudinaryService {

	private Cloudinary cloudinary;

	private String cloudinaryDefault;

	public CloudinaryService() {
		Map<String, String> valuesMap = new HashMap<>();

		valuesMap.put("cloud_name", "dtdwnrdf0");
		valuesMap.put("api_key", "637718139317742");
		valuesMap.put("api_secret", "vw4yreiyxJwa1sUd8C5a24uPFBc");

		cloudinary = new Cloudinary(valuesMap);
	}

	@SuppressWarnings("rawtypes")
	public Map upload(MultipartFile multipartFile, String folder) throws IOException {
		File file = convert(multipartFile);
		Map params = ObjectUtils.asMap("folder", "provina/" + folder, "unique_filename", true, "overwrite", true,
				"resource_type", "image");
		Map result = cloudinary.uploader().upload(file, params);
		file.delete();
		return result;
	}

	@SuppressWarnings("rawtypes")
	public Map delete(String id) throws IOException {
		Map result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
		return result;
	}

	private File convert(MultipartFile multipartFile) throws IOException {
		File file = new File(multipartFile.getOriginalFilename());
		FileOutputStream fo = new FileOutputStream(file);
		fo.write(multipartFile.getBytes());
		fo.close();
		return file;
	}

	public String getCloudinaryDefault() {
		return cloudinaryDefault;
	}
}
