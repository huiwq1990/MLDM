package instance.test;

import java.io.FileReader;
import weka.core.Instances;

public class InstanceTest {
	public static Instances getFileInstances(String fileName) throws Exception {
		FileReader frData = new FileReader(fileName);
		Instances data = new Instances(frData);

		return data;
	}

	public static void main(String[] args) throws Exception {
		Instances instances = getFileInstances("data/contact-lenses.arff");

		// �����ݼ�ȫ�������
//		 System.out.println( instances );

		// ��numInstances���Ի�����ݼ����ж�������
		for (int i = 0; i < instances.numInstances(); i++) {
			// instance( i )�ǵõ���i������
			System.out.println(instances.instance(i));
		}
	}
}