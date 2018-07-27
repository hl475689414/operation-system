/**
 * 
 */
package com.wmq.sys.utils.oss;

import com.aliyun.oss.*;
import com.aliyun.oss.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 *
 * @author xiaojun
 * @version 2015-9-1
 */

/**
 * 
 * @ClassName: OSSUtil
 * 
 * @Description: 该示例代码展示了如果在OSS中创建和删除一个Bucket，以及如何上传和下载一个文件。
 * 
 *               该示例代码的执行过程是： 1. 创建一个Bucket（如果已经存在，则忽略错误码）； 2. 上传一个文件到OSS； 3.
 *               下载这个文件到本地； 4. 清理测试资源：删除Bucket及其中的所有Objects。
 * 
 *               尝试运行这段示例代码时需要注意： 1. 为了展示在删除Bucket时除了需要删除其中的Objects,
 *               示例代码最后为删除掉指定的Bucket，因为不要使用您的已经有资源的Bucket进行测试！ 2.
 *               请使用您的API授权密钥填充ACCESS_ID和ACCESS_KEY常量； 3.
 *               需要准确上传用的测试文件，并修改常量uploadFilePath为测试文件的路径；
 *               修改常量downloadFilePath为下载文件的路径。 4. 该程序仅为示例代码，仅供参考，并不能保证足够健壮。 OSS
 *               Java
 *               API手册：http://aliyun_portal_storage.oss.aliyuncs.com/oss_api
 *               /oss_javahtml/index.html?spm=5176.7150518.1996836753.8.d5TjaG
 *               OSS Java SDK开发包:http://help.aliyun.com/view/13438814.html
 *               OSSClient:www.mvnrepository.com/artifact/cglib/cglib/2.2
 *               图片处理示例:http://img.lskxx.com/111.jpg@100w_100h_90q_1e_1x_1o.jpg
 * @author PineTree
 * @date 2014年12月1日 下午3:23:32
 * @version
 */
public class OSSUtil {

	/**
	 * 创建Bucket
	 * 
	 *            OSSClient对象
	 * @param bucketName
	 *            BUCKET名
	 * @throws OSSException
	 * @throws ClientException
	 */
	public static void ensureBucket(String bucketName)
			throws OSSException, ClientException {
		OSSClient client = new OSSClient(OSSConst.HOST, OSSConst.ACCESSID, OSSConst.ACCESSKEY);
		try {
			client.createBucket(bucketName);
		} catch (ServiceException e) {
			if (!OSSErrorCode.BUCKET_ALREADY_EXISTS.equals(e.getErrorCode())) {
				throw e;
			}
		}
	}

	/**
	 * 删除一个Bucket和其中的Objects
	 * 
	 * @param client
	 *            OSSClient对象
	 * @param bucketName
	 *            Bucket名
	 * @throws OSSException
	 * @throws ClientException
	 */
//	public static void deleteBucket(String bucketName) throws OSSException,
//			ClientException {
//		OSSClient client = new OSSClient(NxConfig.HOST, NxConfig.ACCESSID, NxConfig.ACCESSKEY);
//		ObjectListing ObjectListing = client.listObjects(bucketName);
//		List<OSSObjectSummary> listDeletes = ObjectListing.getObjectSummaries();
//		for (int i = 0; i < listDeletes.size(); i++) {
//			String objectName = listDeletes.get(i).getKey();
//			// 如果不为空，先删除bucket下的文件
//			client.deleteObject(bucketName, objectName);
//		}
//		client.deleteBucket(bucketName);
//	}

	/**
	 * 把Bucket设置成所有人可读
	 * 
	 *            OSSClient对象
	 * @param bucketName
	 *            Bucket名
	 * @throws OSSException
	 * @throws ClientException
	 */
	public static void setBucketPublicReadable(String bucketName)
			throws OSSException, ClientException {
		OSSClient client = new OSSClient(OSSConst.HOST, OSSConst.ACCESSID, OSSConst.ACCESSKEY);
		// 创建bucket
		client.createBucket(bucketName);
		// 设置bucket的访问权限， public-read-write权限
		client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
	}

	/**
	 * 上传文件
	 * 
	 * @param filename 文件名
	 * @param file 文件流
	 */
	public static void uploadFile(String filename, MultipartFile file)throws Exception {
		OSSClient client = new OSSClient(OSSConst.HOST, OSSConst.ACCESSID, OSSConst.ACCESSKEY);
		ObjectMetadata objectMeta = new ObjectMetadata();
		objectMeta.setContentLength(file.getSize());
		objectMeta.setContentType(file.getContentType());
		client.putObject(OSSConst.BUCKET, filename, file.getInputStream(), objectMeta);
	}

	/**
	 * 通过地址上传文件
	 * @param filename
	 * @param url
     */
	public static void uploadCoverImg(String filename, String url){
		OSSClient client = new OSSClient(OSSConst.HOST, OSSConst.ACCESSID, OSSConst.ACCESSKEY);
		client.putObject(OSSConst.BUCKET, filename, new File(url));
	}
	
	/**
	 * 删除文件
	 * 
	 * @param filename 文件名
	 */
	//public static void deleteObject(String filename){
		//OSSClient client = new OSSClient(NxConfig.HOST, NxConfig.ACCESSID, NxConfig.ACCESSKEY);
		//client.deleteObject(NxConfig.BUCKET, filename);
	//}

	/**
	 * 下载文件
	 * @param filename
	 *            文件下载到本地保存的路径
	 * @throws OSSException
	 * @throws ClientException
	 */
	public static void downloadFile(String filename) throws OSSException,
			ClientException {
		OSSClient client = new OSSClient(OSSConst.HOST, OSSConst.ACCESSID, OSSConst.ACCESSKEY);
		client.getObject(new GetObjectRequest(OSSConst.BUCKET, filename), new File(filename));
	}
}
