/*
 * Tree234.cpp
 *
 *  Created on: 30/09/2015
 *      Author: hpiza
 */
#include <iostream>
#include <list>

using namespace std;

class Node234 {
private:
	list<int> values;
	list<int> indexes;
	list<Node234*> children;
	Node234* parent;
public:
	Node234(int value, int index_in_array) {
		values.push_back(value);
		indexes.push_back(index_in_array);
		parent = NULL;
	}

	int getValue(int index) {
		list<int>::iterator ite = values.begin();
		for(int i = 0; i < index; i ++) ite ++;
		return *ite;
	}

	int getIndex(int index) {
		list<int>::iterator ite = indexes.begin();
		for(int i = 0; i < index; i ++) ite ++;
		return *ite;
	}

	Node234* getChild(int index) {
		list<Node234*>::iterator ite = children.begin();
		for(int i = 0; i < index; i ++) ite ++;
		return *ite;
	}

	int getType() {
		return values.size() + 1;
	}

	bool isLeaf() {
		return children.empty();
	}

	int insert(int value, int index) {
		list<int>::iterator vite = values.begin();
		list<int>::iterator iite = indexes.begin();
		for(unsigned int i = 0; i < values.size(); i ++) {
			if(value < *vite) {
				values.insert(vite, value);
				indexes.insert(iite, index);
				return i;
			}
			vite ++;
			iite ++;
		}
		values.push_back(value);
		indexes.push_back(value);
		return values.size() - 1;
	}

};

int main() {
//	Node234 n;
	return 0;
}


