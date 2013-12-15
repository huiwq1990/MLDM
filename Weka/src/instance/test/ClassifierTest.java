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
		 * 结果为2.0的原因是：首先用文本编辑器打开数据集，
		 * 有一行为@attribute contact-lenses {soft, hard, none}，
		 * 而第一个样本为young, myope, no, reduced, none，
		 * 最后一列为类别，也就是contact-lences为类别，
		 * 第一个样本的类别为none，在属性说明中none为第二个,所以为2.0（从0开始数）。
		 */
		System.out.println(classifier.classifyInstance(instance.instance(0)));
	}

	public static void main(String[] args) throws Exception {
		ClassifierTest ctest = new ClassifierTest();

		ctest.getFileInstances("data/contact-lenses.arff");
		ctest.classify();
	}
}