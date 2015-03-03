#!/usr/bin/python

class trie:
    
    def __init__(self):
        self.root = (None , {})

    def insert(self, data):
        trie = self.root[1]
        for ch in data:
            if not trie.has_key(ch):
                trie[ch] = {}
                trie = trie[ch]
            else:
                trie = trie[ch]
        trie[None] = None

    def find(self, word):
        trie = self.root[1]
        for c in word:
            if trie.has_key(c):
                trie = trie[c]
            else:
                return False
        return True


if __name__ == "__main__":
    obj = trie()
    obj.insert("senthil")
    obj.insert("sennat")
    print obj.root[1]
    print obj.find("karthik")