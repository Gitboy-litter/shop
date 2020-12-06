<#import "function.ftl" as func>
<#assign system=vars.system>
server:
  port: 8080
spring:
  application:
    name: ktc-${system} #指定服务名
  datasource:  
    driverClassName: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/xbshop?characterEncoding=UTF8
    username: root
    password: root
  jpa:
    properties:
      hibernate:
        format_sql: true	# 格式化sql
        show_sql: true		# 显示sql
