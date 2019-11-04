package com.sirc.hbase.hbase.boot;

import com.sirc.hbase.hbase.GenericHbaseTemplate;
import com.sirc.hbase.hbase.api.HbaseTemplate;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.security.UserGroupInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.io.IOException;


@org.springframework.context.annotation.Configuration
@EnableConfigurationProperties(HbaseProperties.class)
@ConditionalOnClass(GenericHbaseTemplate.class)
public class HbaseAutoConfiguration {

    private static final String HBASE_ZOOKEEPER_QUORUM = "hbase.zookeeper.quorum";
    private static final String HBASE_ZOOKEEPER_PROPERTY_CLIENTPORT = "hbase.zookeeper.property.clientPort";
    private static final String HBASE_CLIENT_SCANNER_TIMEOUT_PERIOD = "hbase.client.scanner.timeout.period";
    private static final String HBASE_CLIENT_KEYVALUE_MAXSIZE = "hbase.client.keyvalue.maxsize";


    @Autowired
    private HbaseProperties hbaseProperties;

    @Bean
    @ConditionalOnMissingBean(GenericHbaseTemplate.class)
    public GenericHbaseTemplate genericHbaseTemplate() throws IOException {
        return new GenericHbaseTemplate(initConfiguration());
    }


    private Configuration initConfiguration() throws IOException {
        System.setProperty("java.security.krb5.conf","conf/krb5.conf");
        Configuration conf = new Configuration();
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");

        Configuration configuration = HBaseConfiguration.create();

        configuration.set("hadoop.security.authentication","Kerberos");

        configuration.set(HBASE_ZOOKEEPER_QUORUM, this.hbaseProperties.getQuorum());
        configuration.set(HBASE_ZOOKEEPER_PROPERTY_CLIENTPORT, hbaseProperties.getClientPort());
        configuration.set(HBASE_CLIENT_SCANNER_TIMEOUT_PERIOD, hbaseProperties.getPeriod());
        configuration.set(HBASE_CLIENT_KEYVALUE_MAXSIZE,hbaseProperties.getMaxSize());
        UserGroupInformation.loginUserFromKeytab("hbase@GEMS.COM","conf/hbase.keytab");
        return configuration;
    }



}
