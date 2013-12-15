package instance.test;

import java.io.FileReader;

import weka.classifiers.trees.J48;
import weka.classifiers.trees.Id3;
import weka.core.Instances;

public class ClassifierTest {
	private Instances instance = null;

	public void getFileInstances(String fileName) throws Exception {
		FileReader frData = new FileReader(fileName);
		instance = new Instances(frData);

		instance.setClassIndex(instance.numAttributes() - 1);
	}

	public void classify() throws Exception {
		J48 classifier = new J48();
		// NaiveBayes classifier = new NaiveBayes();
		// SMO classifier = new SMO();

		classifier.buildClassifier(instance);
		/*
		 * ���Ϊ2.0��ԭ���ǣ��������ı��༭�������ݼ���
		 * ��һ��Ϊ@attribute contact-lenses {soft, hard, none}��
		 * ����һ������Ϊyoung, myope, no, reduced, none��
		 * ���һ��Ϊ���Ҳ����contact-lencesΪ���
		 * ��һ�����������Ϊnone��������˵����noneΪ�ڶ���,����Ϊ2.0����0��ʼ������
		 */
		System.out.println(classifier.classifyInstance(instance.instance(0)));
	}

	public static void main(String[] args) throws Exception {
		ClassifierTest ctest = new ClassifierTest();

		ctest.getFileInstances("data/contact-lenses.arff");
		ctest.classify();
	}
}