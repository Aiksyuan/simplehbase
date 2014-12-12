simplehbase
=========== 
simplehbase��java��hbase֮����������м���� ��Ҫ�������¹��ܡ�
*  ��������ӳ�䣺java���ͺ�hbase��bytes֮�������ת����
*  �򵥲�����װ����װ��hbase��put,get,scan�Ȳ���Ϊ�򵥵�java������ʽ��
*  hbase query��װ����װ��hbase��filter������ʹ��sql-like�ķ�ʽ����hbase��
*  ��̬query��װ��������myibatis������ʹ��xml���ö�̬����ѯhbase��
*  insert,update֧��: ������hbase��checkAndPut֮�ϡ�
*  hbase��汾֧�֣��ṩ�ӿڿ��Զ�hbase��汾���ݽ��в�ѯ,ӳ�䡣
*  hbase��������֧�֡�
*  hbaseԭ���ӿ�֧�֡�
*  HTablePool����
*  HTable count��sum���ܡ�


Simplehbase is a lightweight ORM framework between java app and hbase.

The main feature of it are following:

	data type mapping: mapping java type to hbase's bytes back and forth.
	
	hbase operation wrapping: warpping hbase's put get scan operation to simple java interface.
	
	hbase query language: using hbase filter, simplehbase can use sql-like style to operate on hbase.
	
	dynamic query: like myibatis, simplehbase can use xml config file to define dynamic query to operate on hbase.
	
	insert update support: provide insert, update on top of checkAndPut.
	
	multiple version support: provide interface to operation on hbase's multiple version.



