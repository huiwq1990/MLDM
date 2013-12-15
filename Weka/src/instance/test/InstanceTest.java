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

		// 把数据集全部输入出
//		 System.out.println( instances );

		// 用numInstances可以获得数据集中有多少样本
		for (int i = 0; i < instances.numInstances(); i++) {
			// instance( i )是得到第i个样本
			System.out.println(instances.instance(i));
		}
	}
}