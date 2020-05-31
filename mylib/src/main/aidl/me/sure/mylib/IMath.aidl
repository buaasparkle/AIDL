// IMath.aidl
package me.sure.mylib;

import me.sure.mylib.Point;

// Declare any non-default types here with import statements

interface IMath {

int add(int a, int b);
Point pointAdd(in Point a, in Point b);
}
