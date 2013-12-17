package com.alipay.simplehbase.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.log4j.Logger;

import com.alipay.simplehbase.exception.SimpleHBaseException;
import com.alipay.simplehbase.util.ConfigUtil;
import com.alipay.simplehbase.util.StringUtil;

/**
 * HbaseDataSource��ʾһ��hbase����Դ��
 * 
 * <pre>
 * 1 ���������Ϣ��hbase��Ҫ��2�������
 * 
 * hbaseԭ�����������ͨ�������ļ�·��hbaseConfigFilePaths���߱����������hbaseConfig�����á�
 * ��key��ͬʱ��hbaseConfig�����ȼ�����hbaseConfigFilePaths��
 * 
 * simpleHbase˽�����������ͨ�������ļ�·��dataSourceConfigFilePath���߱����������dataSourceConfig�����á�
 * ��key��ͬʱ��dataSourceConfig�����ȼ�����dataSourceConfigFilePath��
 * 
 * �����ļ��ĸ�ʽ��ÿһ��Ϊkey=value��
 * 
 * 
 * 2 ʹ�÷�ʽ��
 * Ŀǰʹ��HbaseDataSource��Ҫ��2�ַ�ʽ:
 *   1 ʹ��spring���á�����ʱ��Ӧ��ʹ��init������ʼ��HBaseDataSource��
 *   2 Ӧ������HBaseDataSource�����ԣ�����init�������г�ʼ����
 * </pre>
 * 
 * @author xinzhi
 * @version $Id: HBaseDataSource.java 2013-09-11 ����11:27:31 xinzhi $
 * */
public class HBaseDataSource {

    /** log. */
    private static Logger       log                   = Logger.getLogger(HBaseDataSource.class);
    //----------config--------------
    /**
     * dataSourceΨһid��
     * */
    @ConfigAttr
    private String              id;
    /**
     * hbaseԭ�������ļ�·����
     * */
    @ConfigAttr
    private List<String>        hbaseConfigFilePaths;
    /**
     * hbaseԭ�������
     * */
    @ConfigAttr
    private Map<String, String> hbaseConfig;
    /**
     * simplehbase��datasource�����ļ�·����
     * */
    @ConfigAttr
    private String              dataSourceConfigFilePath;
    /**
     * simplehbase��datasource�����
     * */
    @ConfigAttr
    private Map<String, String> dataSourceConfig;

    //---------------------------runtime-------------------------
    /**
     * ����hbase�������
     * */
    private Map<String, String> finalHbaseConfig      = new HashMap<String, String>();
    /**
     * ����simplehbase�������
     * */
    private Map<String, String> finalDataSourceConfig = new HashMap<String, String>();

    /**
     * hbase���á�
     * */
    private Configuration       hbaseConfiguration;

    /**
     * HTablePoolHolder��
     * */
    private HTablePoolHolder    htablePoolHolder;

    /**
     * ��ʼ��dataSource��
     * */
    public void init() {
        try {
            parseConfig();
            initHTablePoolHolder();
            log.info(this);
        } catch (Exception e) {
            log.error(e);
            throw new SimpleHBaseException(e);
        }
    }

    /**
     * �õ�ָ��������HTableInterface��
     * 
     * @param tableName ������
     * @return HTableInterface��
     * */
    public HTableInterface getHTable(String tableName) {
        return htablePoolHolder.getHTable(tableName);
    }

    /**
     * �õ�һ��HBaseAdmin��
     * */
    public HBaseAdmin getHBaseAdmin() {
        try {
            return new HBaseAdmin(hbaseConfiguration);
        } catch (Exception e) {
            log.error(e);
            throw new SimpleHBaseException(e);
        }
    }

    /**
     * ���������
     * */
    private void parseConfig() {
        try {
            if (hbaseConfigFilePaths != null) {
                for (String filePath : hbaseConfigFilePaths) {
                    finalHbaseConfig
                            .putAll(ConfigUtil.loadConfigFile(filePath));
                }
            }

            if (hbaseConfig != null) {
                finalHbaseConfig.putAll(hbaseConfig);
            }

            hbaseConfiguration = HBaseConfiguration.create();
            for (Map.Entry<String, String> entry : finalHbaseConfig.entrySet()) {
                hbaseConfiguration.set(entry.getKey(), entry.getValue());
            }

            finalDataSourceConfig.putAll(ConfigUtil
                    .loadConfigFile(dataSourceConfigFilePath));
            if (dataSourceConfig != null) {
                finalDataSourceConfig.putAll(dataSourceConfig);
            }

        } catch (Exception e) {
            log.error("parseConfig error.", e);
            throw new SimpleHBaseException("parseConfig error.", e);
        }
    }

    /**
     * ��ʼ��HTablePoolHolder��
     * */
    private void initHTablePoolHolder() {
        try {
            htablePoolHolder = new HTablePoolHolder();
            htablePoolHolder.init(hbaseConfiguration, finalDataSourceConfig);
        } catch (Exception e) {
            log.error("initHTablePoolHolder error.", e);
            throw new SimpleHBaseException("initHTablePoolHolder error.", e);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getHbaseConfigFilePaths() {
        return hbaseConfigFilePaths;
    }

    public void setHbaseConfigFilePaths(List<String> hbaseConfigFilePaths) {
        this.hbaseConfigFilePaths = hbaseConfigFilePaths;
    }

    public Map<String, String> getHbaseConfig() {
        return hbaseConfig;
    }

    public void setHbaseConfig(Map<String, String> hbaseConfig) {
        this.hbaseConfig = hbaseConfig;
    }

    public String getDataSourceConfigFilePath() {
        return dataSourceConfigFilePath;
    }

    public void setDataSourceConfigFilePath(String dataSourceConfigFilePath) {
        this.dataSourceConfigFilePath = dataSourceConfigFilePath;
    }

    public Map<String, String> getDataSourceConfig() {
        return dataSourceConfig;
    }

    public void setDataSourceConfig(Map<String, String> dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("---------------datasource--------------------------\n");

        StringUtil.append(sb, "#id#", id);
        StringUtil.append(sb, "#hbaseConfigFilePaths#", hbaseConfigFilePaths);
        StringUtil.append(sb, "#hbaseConfig#", hbaseConfig);
        StringUtil.append(sb, "#dataSourceConfigFilePath#",
                dataSourceConfigFilePath);
        StringUtil.append(sb, "#dataSourceConfig#", dataSourceConfig);

        StringUtil.append(sb, "#finalHbaseConfig#", finalHbaseConfig);
        StringUtil.append(sb, "#finalDataSourceConfig#", finalDataSourceConfig);

        sb.append("---------------datasource--------------------------\n");
        return sb.toString();
    }
}
