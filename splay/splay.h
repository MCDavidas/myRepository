//
// Created by User on 05.05.2019.
//

#ifndef MY_TREE_SPLAY_H
#define MY_TREE_SPLAY_H


template <class T>
class Tree
{

private:

    struct Leaf
    {
        T value;
        Leaf* left;
        Leaf* right;
        Leaf* parent;
    };

    Leaf* root;

    void set_parent( Leaf* child, Leaf* parent )
    {
        if ( child != NULL )
            child->parent = parent;
    }

    void keep_parent( Leaf* v )
    {
        if ( v != NULL )
        {
            set_parent( v->left, v );
            set_parent( v->right, v );
        }
    }

    void UpdRoot ()
    {
        while ( root != NULL && root->parent != NULL )
            root = root->parent;
    }

    void Destroy ( Leaf* v )
    {
        if ( v == NULL )
            return;

        Destroy( v->left );
        Destroy( v->right );

        delete[] v;
    }

    Leaf* FindLeaf ( Leaf* v, T curr )
    {
        if ( v == NULL || v->value == curr )
            return v;

        if ( v->value > curr && v->left != NULL )
            return FindLeaf( v->left, curr );

        if ( v->value < curr && v->right != NULL )
            return FindLeaf( v->right, curr );

        return v;
    }

    void Rotate ( Leaf* v )
    {
        Leaf* ancestor = v->parent;

        if ( ancestor == NULL )
            return;

        if ( ancestor->parent != NULL )
        {
            if ( (ancestor->parent)->right == ancestor )
                (ancestor->parent)->right = v;
            else
                (ancestor->parent)->left = v;
        }

        if ( ancestor->left == v )
        {
            ancestor->left = v->right;
            v->right = ancestor;
        }
        else
        {
            ancestor->right = v->left;
            v->left = ancestor;
        }

        Leaf* save_ancestor = ancestor->parent;
        keep_parent(v);
        keep_parent(ancestor);
        v->parent = save_ancestor;

        UpdRoot();
    }

    void Splay ( Leaf* v )
    {
        if ( v == NULL )
            return;

        Leaf* ancestor = v->parent;

        if ( ancestor == NULL )
            return;

        if ( ancestor->parent == NULL )
        {
            Rotate( v );
            return;
        }

        Leaf* second_ancestor = ancestor->parent;

        if ( ( second_ancestor->right == ancestor && ancestor->right == v ) ||
            (second_ancestor->left == ancestor && ancestor->left == v ) )
        {
            Rotate( ancestor );
            Rotate( v );
        }
        else
        {
            Rotate( v );
            Rotate( v );
        }
    }

    std::pair<Leaf*, Leaf*> Split ( T key )
    {
        if ( root == NULL )
            return {NULL, NULL};

        Leaf* v = FindLeaf( root, key );
        Splay( v );

        if ( root->value == key )
        {
            if ( root->left != NULL )
                (root->left)->parent = NULL;
            if ( root->right != NULL )
                (root->right)->parent = NULL;
            return { root->left, root->right };
        }

        if ( root->value < key )
        {
            Leaf* right = root->right;
            root->right = NULL;
            if ( right != NULL )
                right->parent = NULL;
            return { root, right };
        }

        if ( root->value > key )
        {
            Leaf* left = root->left;
            root->left = NULL;
            if ( left != NULL )
                left->parent = NULL;
            return { left, root };
        }

        return {NULL, NULL};
    }

    Leaf* Merge ( Leaf* a, Leaf* b )
    {
        if ( a == NULL )
            return b;
        if ( b == NULL )
            return a;

        Leaf* v = FindLeaf( b, a->value );
        Splay( v );

        b->left = a;
        a->parent = b;
        return b;
    }


public:

    Tree () { root = NULL; }
    ~Tree () { Destroy(root); }

    bool Find ( T curr )
    {
        Leaf* aim = FindLeaf( root, curr );
        Splay( aim );

        if (aim != NULL && aim->value == curr)
            return true;
        else
            return false;
    }

    void Insert ( T curr )
    {
        std::pair< Leaf*, Leaf* > p = Split( curr );

        Leaf* v = new Leaf;
        v->value = curr;
        v->left = p.first;
        v->right = p.second;
        v->parent = NULL;

        if ( p.first != NULL )
            (p.first)->parent = v;
        if ( p.second != NULL )
            (p.second)->parent = v;

        root = v;
    }

    void Delete ( T curr )
    {
        Leaf* v = FindLeaf( root, curr );

        if ( v->value != curr )
            return;

        Splay( v );
        root->left->parent = NULL;
        root->right->parent = NULL;
        root = Merge( root->left, root->right );
    }

};


#endif //MY_TREE_SPLAY_H
