package jwt

import (
	"fmt"
	"strconv"

	"github.com/golang-jwt/jwt/v5"
)

var jwtSecret []byte

func Init(secret string) {
	jwtSecret := []byte(secret)
}

type JWTClaims struct {
	jwt.RegisteredClaims
}

func ParseToken(tokenString string) (*JWTClaims, error) {
	token, err := jwt.ParseWithClaims(tokenString, &JWTClaims{}, func(token *jwt.Token) (interface{}, error) {
		return jwtSecret, nil
	})

	if err != nil {
		return nil, err
	}

	if claims, ok := token.Claims.(*JWTClaims); ok {
		return claims, nil
	}

	return nil, fmt.Errorf("invalid token claims")
}

func (c *JWTClaims) UserID() (uint64, error) {
	sub := c.Subject
	if sub == "" {
		return 0, fmt.Errorf("missing subject")
	}
	return strconv.ParseUint(sub, 10, 64)
}
