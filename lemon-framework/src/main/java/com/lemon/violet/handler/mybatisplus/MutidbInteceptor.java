//package com.lemon.violet.handler.mybatisplus;
//
//
//import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.cache.CacheKey;
//import org.apache.ibatis.executor.Executor;
//import org.apache.ibatis.mapping.BoundSql;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.mapping.SqlCommandType;
//import org.apache.ibatis.mapping.SqlSource;
//import org.apache.ibatis.plugin.*;
//import org.apache.ibatis.reflection.MetaObject;
//import org.apache.ibatis.session.ResultHandler;
//import org.apache.ibatis.session.RowBounds;
//import org.springframework.stereotype.Service;
//
//import java.util.Properties;
//import java.util.regex.Pattern;
//
//
///**
// * 跨库查询拦截
// */
//@Slf4j
//@Service
//@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class,
//        Object.class,
//        RowBounds.class,
//        ResultHandler.class}),@Signature(type = Executor.class, method = "query", args = {MappedStatement.class,
//        Object.class,
//        RowBounds.class,
//        ResultHandler.class,
//        CacheKey.class,
//        BoundSql.class})})
//public class MutidbInteceptor extends AbstractSqlParserHandler implements Interceptor {
//    private static Pattern PATTERN_USER = Pattern.compile("(\\s|\r|\n|\t)(cm_|CM_)");
//    private static Pattern PATTERN_ADMIN = Pattern.compile("(\\s|\r|\n|\t)(co_|app_|CO_|APP_|sys_|SYS_)");
//
//    /**
//     * 拦截查询sql，对含有market_admin和market_user双库的联合查询进行数据库替换
//     * 拦截顺序：Executor > ParameterHandler > StatementHandler > ResultSetHandler
//     * @param invocation 拦截器执行器
//     * @return
//     * @throws Throwable 异常信息
//     */
//    @Override
//    public Object intercept(Invocation invocation) throws Throwable {
//
//        //  获取 invocation 传递的参数
//        Object[] args = invocation.getArgs();
//        MappedStatement ms = (MappedStatement) args[0];
//
//        //sql拼接
//        BoundSql boundSql = ms.getBoundSql(args[1]);
//        String originalSql = boundSql.getSql();
//        String sql = "";
//
//        //是否select操作
//        if(SqlCommandType.SELECT == ms.getSqlCommandType()){
//            sql = buildQuerySql(originalSql);
//        }
//        boundSql = new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
//        Object[] queryArgs = invocation.getArgs();
//
//
////        //解决MyBatis拦截器插件foreach 参数失效 start
////        if (Reflections.getFieldValue(boundSql, "metaParameters") != null) {
////            MetaObject mo = (MetaObject) Reflections.getFieldValue(boundSql, "metaParameters");
////            Reflections.setFieldValue(boundSql, "metaParameters", mo);
////        }
//        return invocation.proceed();
//    }
//
//    /**
//     * 构建新的MappedStatement
//     *
//     * @param ms
//     * @param newSqlSource
//     * @return
//     */
//    private MappedStatement newMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
//        MappedStatement.Builder builder = new
//                MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
//        builder.resource(ms.getResource());
//        builder.fetchSize(ms.getFetchSize());
//        builder.statementType(ms.getStatementType());
//        builder.keyGenerator(ms.getKeyGenerator());
//        if (ms.getKeyProperties() != null && ms.getKeyProperties().length > 0) {
//            builder.keyProperty(ms.getKeyProperties()[0]);
//        }
//        builder.timeout(ms.getTimeout());
//        builder.parameterMap(ms.getParameterMap());
//        builder.resultMaps(ms.getResultMaps());
//        builder.resultSetType(ms.getResultSetType());
//        builder.cache(ms.getCache());
//        builder.flushCacheRequired(ms.isFlushCacheRequired());
//        builder.useCache(ms.isUseCache());
//        return builder.build();
//    }
//
//    /***
//     * 定义拦截的类 Executor、ParameterHandler、StatementHandler、ResultSetHandler当中的一个
//     * @param target 需要拦截的类
//     * @return
//     */
//    @Override
//    public Object plugin(Object target) {
//        if (target instanceof Executor) {
//            return Plugin.wrap(target, this);
//        }
//        return target;
//
//    }
//
//    /**
//     * 属性相关操作
//     * 设置和自定义属性值
//     *
//     * @param properties 属性值
//     */
//    @Override
//    public void setProperties(Properties properties) {
//    }
//
//    /**
//     * 对含有market_admin和market_user双库的联合查询进行数据库替换，如co_xxx表替换为market_admin.co_xxx
//     *
//     * @param originalSql 初始化sql
//     * @return 替换后的sql
//     */
//    private String buildQuerySql(String originalSql) {
//        String lowcaseSql = originalSql.toLowerCase();
//        if (PATTERN_USER.matcher(lowcaseSql).find() && PATTERN_ADMIN.matcher(lowcaseSql).find()){
//            return originalSql.replaceAll("co_","market_admin.co_")
//                    .replaceAll("CO_","market_admin.CO_")
//                    .replaceAll("app_","market_admin.app_")
//                    .replaceAll("APP_","market_admin.APP_")
//                    .replaceAll("SYS_","market_admin.SYS_")
//                    .replaceAll("sys_","market_admin.sys_")
//                    .replaceAll("cm_","market_user.cm_")
//                    .replaceAll("CM_","market_user.CM_");
//        }
//
//        return originalSql;
//    }
//
//
//    /**
//     * 定义一个内部辅助类，作用是包装 SQL
//     */
//    class BoundSqlSqlSource implements SqlSource {
//        private BoundSql boundSql;
//
//        public BoundSqlSqlSource(BoundSql boundSql) {
//            this.boundSql = boundSql;
//        }
//
//        @Override
//        public BoundSql getBoundSql(Object parameterObject) {
//            return boundSql;
//        }
//
//    }
//
//}
