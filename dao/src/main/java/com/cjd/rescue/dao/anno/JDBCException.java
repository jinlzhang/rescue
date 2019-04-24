package com.cjd.rescue.dao.anno;


import java.lang.annotation.*;

@Documented
@Inherited
@Target({ ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JDBCException {


}
