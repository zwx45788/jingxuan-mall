package response

import (
	"net/http"

	"github.com/gin-gonic/gin"
)

type Response struct {
	Code    int    `json:"code"`
	Message string `json:"message,omitempty"`
	Data    any    `json:"data,omitempty"`
}

func Success(c *gin.Context, data any) {
	response(c, 0, "success", data)
}

func Error(c *gin.Context, message string) {
	response(c, -1, message, nil)
}

func BadRequest(c *gin.Context, message string) {
	response(c, 400, message, nil)
}

func ServiceUnavailable(c *gin.Context, message string) {
	response(c, 503, message, nil)
}

func response(c *gin.Context, code int, message string, data any) {
	c.JSON(http.StatusOK, Response{
		Code:    code,
		Message: message,
		Data:    data,
	})
}
