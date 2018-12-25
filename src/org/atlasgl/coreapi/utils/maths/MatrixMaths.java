package org.atlasgl.coreapi.utils.maths;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class MatrixMaths {
	
	public static Matrix4f createTransformationMatrix(Vector3f translation, Vector3f rotation, Vector3f scale) {
		
		Matrix4f matrix = new Matrix4f().identity();
		matrix.translate(translation.x, translation.y, translation.z);
		matrix.rotateAffineXYZ(rotation.x, rotation.y, rotation.z);
		matrix.scale(scale.x, scale.y, scale.z);
		return matrix;
		
	}

}
