# mybatis-geneator-tool
## ����
- ��ͨ���˹����Զ�����Mybatis XML�ļ���dao��class��
- �ɽ��BaseDao��ɻ���������BaseDao֧�ֵķ�������
```java
public interface BaseDao<T> {
	//����ʵ�����������¼
	long insert(T entity);
	//�����������
	long insert(List<T> list);
	//����ʵ���Ӧ�ļ�¼
	long update(T entity);
	//����ID���Ҽ�¼
	T getById(long id);
	//����IDɾ����¼
	int deleteById(long id);
	//��ҳ��ѯ
	PageBean<T> listPage(PageParam pageParam, Map<String, Object> paramMap);
	//����������ҳ��ѯ
	PageBean<T> listPage(PageParam pageParam, Map<String, Object> paramMap, String sqlId);
	//����������ѯ�������б�
	List<T> listBy(Map<String, Object> paramMap);
	List<T> listBy(Map<String, Object> paramMap, String sqlId);
	//����������ѯ���ص�������
	T getBy(Map<String, Object> paramMap);
	T getBy(Map<String, Object> paramMap, String sqlId);
}
```

## Quick start
1����дʵ��

2����Main������ָ��ʵ����class������

3������main����

## ���ڼƻ�
1������JDBC���Զ����ӵ�mysql������ʵ�塢dao��XML Mapper�ļ�