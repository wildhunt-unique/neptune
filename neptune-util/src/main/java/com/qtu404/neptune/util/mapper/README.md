1. 调用MapperBuilder的setter方法，设置数据库、账号、密码、mapper文件输出路径
2. 调用MapperBuilder的静态方法build构建mapper.xml文件，有两个参数，一个是表名，一个是mapper对应的do类名，非全限定名称，所以需要配置mybatis别名。
3. 构建完mapper文件之后，打开，