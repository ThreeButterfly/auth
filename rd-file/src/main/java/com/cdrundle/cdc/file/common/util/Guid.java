/******************************************************************
 *
 *    Company:   成都市润东实业有限公司 软件开发部
 *
 *    Filename:    Guid.java
 *
 *    Copyright:   Copyright (c) 2001-2014
 *
 *    @author:     Tjee
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年5月23日 下午11:59:20
 *
 *    Revision:
 *
 *    2017年5月23日 下午11:59:20
 *        - first revision
 *
 *****************************************************************/
package com.cdrundle.cdc.file.common.util;

import java.util.UUID;

/**
 *<P>
 *  TODO：说明与描述
 *<P>
 * @author Tjee
 * @CreateDate 2017年5月23日 下午1:59:20
 */
public class Guid {
    public static String getIdByUUId() {
        int machineId = 1;
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {
            hashCodeV = - hashCodeV;
        }
        // 0 代表前面补充0     
        // 4 代表长度为4     
        // d 代表参数为正数型
        return machineId + String.format("%015d", hashCodeV);
    }
    public static void main(String[] args) {
        System.out.println(getIdByUUId());
    } 
}
