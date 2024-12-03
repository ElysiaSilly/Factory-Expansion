package com.teamcitrus.factory_expansion.core.util;

import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;
import org.joml.Vector3i;

public class MathUtils {

    public static Vector3f vec3ToVec3f(Vec3 vec3) {
        return new Vector3f((float) vec3.x, (float) vec3.y, (float) vec3.z);
    }

    public static Vector3i vec3ToVec3i(Vec3 vec3) {
        return new Vector3i((int) vec3.x, (int) vec3.y, (int) vec3.z);
    }

    public static float distance(Vector3f start, Vector3f end) {
        return Vector3f.distance(start.x, start.y, start.z, end.x, end.y, end.z);
    }

    public static float distance(Vec3 start, Vec3 end) {
        Vector3f startf = vec3ToVec3f(start);
        Vector3f endf = vec3ToVec3f(end);
        return Vector3f.distance(startf.x, startf.y, startf.z, endf.x, endf.y, endf.z);
    }

    public static float castToRange(float oldMin, float oldMax, float newMin, float newMax, float value) {
        return (((value - oldMin) * (newMax - newMin)) / (oldMax - oldMin)) + newMin;
    }
}
