package rendering;

import java.util.Comparator;

public class MTPComparator implements Comparator<ModelTransformPair> {

	@Override
	public int compare(ModelTransformPair o1, ModelTransformPair o2) {
		// TODO Auto-generated method stub
		return o1.transform.z < o2.transform.z ? 1 : -1;
	}

}
