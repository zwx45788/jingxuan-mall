package oss

import (
	"errors"
	"fmt"
	"mime/multipart"
	"shopping/internal/config"
	"shopping/internal/logger"
	"strings"
	"time"

	"github.com/aliyun/aliyun-oss-go-sdk/oss"
	"github.com/google/uuid"
)

type OssClient struct {
	endpoint        string
	accessKeyID     string
	accessKeySecret string
	bucketName      string
	conn            *oss.Client
}

var ossClient *OssClient

func Init(cfg *config.OssConfig) error {

	if cfg == nil {
		logger.Log.Error("配置未加载")
	}
	conn, err := oss.New(cfg.Endpoint, cfg.AccessKeyId, cfg.AccessKeySecret)
	if err != nil {
		logger.Log.Error("oss连接失败")
		return err
	}

	ossClient.endpoint = cfg.Endpoint
	ossClient.accessKeyID = cfg.AccessKeyId
	ossClient.accessKeySecret = cfg.AccessKeySecret
	ossClient.conn = conn

	return nil
}

func (c *OssClient) UploadFile(fileHeader *multipart.FileHeader) (string, error) {

	filename := fileHeader.Filename
	if filename == "" {
		return "", fmt.Errorf("文件名不能为空")
	}

	ext := ""
	if idx := strings.LastIndex(filename, "."); idx != -1 {
		ext = filename[idx:]
	}

	src, err := fileHeader.Open()
	if err != nil {
		logger.Log.Error("文件读取失败")
		return "", err
	}

	now := time.Now()
	datePath := now.Format("2006/01/02")

	filePath := fmt.Sprintf("%s/%s%s", datePath, uuid.New(), ext)

	bucket, err := c.conn.Bucket(c.bucketName)

	if err != nil {
		logger.Log.Error("获取bucket失败")
		return "", err
	}

	err = bucket.PutObject(filePath, src)

	if err != nil {
		logger.Log.Error("文件上传失败")
	}
	return filePath, nil
}

func (c *OssClient) DownloadFile(filePath string) (string, error) {

	bucket, err := c.conn.Bucket(c.bucketName)
	if err != nil {
		logger.Log.Error("获取bucket失败")
		return "", err
	}

	expires := int64(15 * 60 * time.Second)

	url, err := bucket.SignURL(filePath, oss.HTTPGet, expires)

	if err != nil {
		logger.Log.Error("签名失败")
		return "", err
	}

	return url, nil

}

func GetOssClient() (*OssClient, error) {
	if ossClient == nil {
		logger.Log.Error("未初始化OssClient")
		return nil, errors.New("oss client 未初始化，请先调用 Init 方法")
	}

	return ossClient, nil
}
