package com.wmq.sys.utils.base;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * Project：common<br>
 * Packeage：com.wmq.common.base <br>
 * Class：Base  <br>
 * Description：Some message about this Class<br>
 ******************************************************<br>
 * <b>Methods：</b><br>
 *   This method is abstract class. <br>
 *   Its Contains methods,static phrases,charset frequently used .  <br>
 ******************************************************<br>
 * User:   lit   2017  <br>
 * Create Date:2017年3月20日 <br>
 * Company： www.gzskxx.com<br>
 * Version： V2.0<br>
 *******************************************************<br>
 *
 */
public abstract class Base {

    public static final String DEFAULT_CHARSET_UTF8="UTF-8";
    public static final String DEFAULT_SEPARATOR = "/";
    public static final String DEFAULT_BLANK = "";
    public static final String DEFAULT_DELIMITED_ = ",";
    

    /**Log object**/
    protected final Logger logger = LogManager.getLogger(getClass());

    /**This method used for create a new <b>Date</b> Object.**/
    public static Date getDate(){
    	return new Date();
    }

    /**
     *
     * Date：2017年3月20日<br>
     * Version：v1.0<br>
     * Method： notEmpty (Short description.) <br>
     * <b>Description</b><br>
     *	notEmpty(null)     = false <br>
     *	notEmpty("")       = false <br>
     *	notEmpty("  ")     = false <br>
     *	notEmpty("OK")     = true <br>
     *  notEmpty("   OK")  = true <br>
     *
     * ===============================================<br>
     * CreateDate：2017年3月20日 下午3:06:54 <br>
     * User:  lit<br>
     * ===============================================<br>
     * User:  lit<br>
     * Changed: <b>(change message.) </b><br>
     * ===============================================<br>
     * boolean
     * @param s
     * @return
     * @Exception Exception object<br>
     */
    public static boolean notEmpty(String s){
        return StringUtils.isNotBlank(s);
    }

    /**
     *
     * Date：2017年3月20日<br>
     * Version：v1.0<br>
     * Method： empty (Short description.) <br>
     * <b>Description</b><br>
     *	notEmpty(null)     = true <br>
     *	notEmpty("")       = true <br>
     *	notEmpty("  ")     = true <br>
     *	notEmpty("OK")     = false <br>
     *  notEmpty("   OK")  = false <br>
     * ===============================================<br>
     * CreateDate：2017年3月20日 下午6:36:58 <br>
     * User:  lit<br>
     * ===============================================<br>
     * User:  lit<br>
     * Changed: <b>(change message.) </b><br>
     * ===============================================<br>
     * boolean
     * @param s
     * @return
     * @Exception Exception object<br>
     */
    public static boolean empty(String s){
        return StringUtils.isBlank(s);
    }
    /**
     *
     * Date：2017年3月20日<br>
     * Version：v1.0<br>
     * Method： notEmpty (Short description.) <br>
     * <b>Description</b><br>
     *	Check a object is not null<br>
     *
     * ===============================================<br>
     * CreateDate：2017年3月20日 下午6:39:02 <br>
     * User:  lit<br>
     * ===============================================<br>
     * User:  lit<br>
     * Changed: <b>(change message.) </b><br>
     * ===============================================<br>
     * boolean
     * @param obj
     * @return
     * @Exception Exception object<br>
     */
    public static boolean notEmpty(Object obj){
        return null != obj;
    }

    /**
     *
     * Date：2017年3月20日<br>
     * Version：v1.0<br>
     * Method： mpty (Short description.) <br>
     * <b>Description</b><br>
     *	Check a object is not null <br>
     *
     * ===============================================<br>
     * CreateDate：2017年3月20日 下午6:43:31 <br>
     * User:  lit<br>
     * ===============================================<br>
     */
    public static boolean empty(Object obj){
        return null == obj;
    }
    /**
     *
     * Date：2017年3月20日<br>
     * Version：v1.0<br>
     * Method： empty (Short description.) <br>
     * <b>Description</b><br>
     *	Check a List<T> Object is empty. <br>
     *
     * ===============================================<br>
     * CreateDate：2017年3月20日 下午6:52:43 <br>
     * User:  lit<br>
     * ===============================================<br>
     */
    public static boolean empty(List<?> list){
        return null == list || list.isEmpty();
    }
    /**
     *
     * Date：2017年3月20日<br>
     * Version：v1.0<br>
     * Method： notEmpty (Short description.) <br>
     * <b>Description</b><br>
     *	Check a List<T> Object is not empty. <br>
     *
     * ===============================================<br>
     * CreateDate：2017年3月20日 下午6:53:35 <br>
     * User:  lit<br>
     * ===============================================<br>
     */
    public static boolean notEmpty(List<?> list){
        return null != list && ! list.isEmpty();
    }
    /**
     *
     * Date：2017年3月20日<br>
     * Version：v1.0<br>
     * Method： empty (Short description.) <br>
     * <b>Description</b><br>
     *	Check a Map<?,?> Object is empty. <br> <br>
     *
     * ===============================================<br>
     * CreateDate：2017年3月20日 下午6:56:33 <br>
     * User:  lit<br>
     * ===============================================<br>
     */
    public static boolean empty(Map<?,?> map){
        return null == map || map.isEmpty();
    }
    /**
     *
     * Date：2017年3月20日<br>
     * Version：v1.0<br>
     * Method： notEmpty (Short description.) <br>
     * <b>Description</b><br>
     *	Check a Map<?,?> Object is not empty.  <br>
     *
     * ===============================================<br>
     * CreateDate：2017年3月20日 下午7:01:06 <br>
     * User:  lit<br>
     * ===============================================<br>
     */
    public static boolean notEmpty(Map<?,?> map){
        return null != map && ! map.isEmpty();
    }

    /**
     *
     * Date：2017年3月20日<br>
     * Version：v1.0<br>
     * Method： notEmpty (Short description.) <br>
     * <b>Description</b><br>
     *	Check a file is null or not exist. <br>
     *
     * ===============================================<br>
     * CreateDate：2017年3月20日 下午7:08:06 <br>
     * User:  lit<br>
     * ===============================================<br>
     */
    public static boolean empty(File file){
        return null == file || !file.exists();
    }

    /**
     *
     * Date：2017年3月20日<br>
     * Version：v1.0<br>
     * Method： notEmpty (Short description.) <br>
     * <b>Description</b><br>
     *	Check a file is not null and exist. <br>
     *
     * ===============================================<br>
     * CreateDate：2017年3月20日 下午7:09:12 <br>
     * User:  lit<br>
     * ===============================================<br>
     */
    public static boolean notEmpty(File file){
        return null != file && file.exists();
    }

    /**
     *
     * Date：2017年3月20日<br>
     * Version：v1.0<br>
     * Method： empty (Short description.) <br>
     * <b>Description</b><br>
     *	Check a Array Objects is empty. <br>
     *
     * ===============================================<br>
     * CreateDate：2017年3月20日 下午7:04:13 <br>
     * User:  lit<br>
     * ===============================================<br>
     */
    public static boolean empty(Object[] objs){
        return null == objs || 0 == objs.length;
    }

    /**
     *
     * Date：2017年3月20日<br>
     * Version：v1.0<br>
     * Method： notEmpty (Short description.) <br>
     * <b>Description</b><br>
     *	Check a Array Objects is not empty. <br>
     *
     * ===============================================<br>
     * CreateDate：2017年3月20日 下午7:04:53 <br>
     * User:  lit<br>
     * ===============================================<br>
     */
    public static boolean notEmpty(Object[] objs){
        return null != objs && 0 != objs.length;
    }

}
