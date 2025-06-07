#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>
#include <cctype>
#include <string>

enum class TokenType { Word, Number, Punctuation, End };
struct Token {
    TokenType type;
    std::string text;
};

std::vector<Token> tokenize(std::istream& in){
    std::vector<Token> tokens;
    char ch;
    while(in.get(ch)){
        if(std::isspace(static_cast<unsigned char>(ch))) continue;
        if(std::isalpha(static_cast<unsigned char>(ch)) || ch=='_'){
            std::string word(1,ch);
            while(in.peek()!=EOF && (std::isalnum(in.peek()) || in.peek()=='_')){
                word.push_back(in.get());
            }
            tokens.push_back({TokenType::Word, word});
        }else if(std::isdigit(static_cast<unsigned char>(ch))){
            std::string num(1,ch);
            while(in.peek()!=EOF && std::isdigit(in.peek())){
                num.push_back(in.get());
            }
            tokens.push_back({TokenType::Number, num});
        }else{
            tokens.push_back({TokenType::Punctuation, std::string(1,ch)});
        }
    }
    tokens.push_back({TokenType::End, ""});
    return tokens;
}

int main(int argc, char** argv){
    std::string path = argc > 1 ? argv[1] : "samples/test001.c";
    std::ifstream in(path);
    if(!in.is_open()){
        std::cerr << "Unable to open " << path << "\n";
        return 1;
    }
    auto tokens = tokenize(in);
    for(const auto& t : tokens){
        switch(t.type){
            case TokenType::Word:
                std::cout << "WORD:" << t.text << "\n";
                break;
            case TokenType::Number:
                std::cout << "NUMBER:" << t.text << "\n";
                break;
            case TokenType::Punctuation:
                std::cout << "PUNC:" << t.text << "\n";
                break;
            default:
                break;
        }
    }
    return 0;
}
