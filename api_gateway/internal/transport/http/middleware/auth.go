package middleware

import (
	"net/http"
	"shopping/internal/pkg/jwt"
	"strings"

	"github.com/gin-gonic/gin"
	"github.com/gorilla/websocket"
)

func Auth() gin.HandlerFunc {

	return func(c *gin.Context) {
		authHeader := c.GetHeader("Authorization")
		var tokenString string
		if authHeader != "" {
			parts := strings.SplitN(authHeader, " ", 2)
			if !(len(parts) == 2 && parts[0] == "Bearer") {
				c.JSON(http.StatusUnauthorized, gin.H{"error": "Authorization header format must be Bearer {token}"})
				c.Abort()
				return
			}
			tokenString = parts[1]
		} else {
			tokenString = c.Query("token")
		}

		if tokenString == "" {
			if websocket.IsWebSocketUpgrade(c.Request) {
				c.AbortWithStatus(http.StatusUnauthorized)
			} else {
				c.JSON(http.StatusUnauthorized, gin.H{"error": "token is required"})
			}
			return
		}

		claims, err := jwt.ParseToken(tokenString)
		if err != nil {
			if websocket.IsWebSocketUpgrade(c.Request) {
				c.AbortWithStatus(http.StatusUnauthorized)
			} else {
				c.JSON(http.StatusUnauthorized, gin.H{"error": "Invalid token"})
			}
			return
		}

		userid, err := claims.UserID()
		if err != nil {
			return
		}
		c.Set("user_id", userid)
		c.Next()
	}
}
