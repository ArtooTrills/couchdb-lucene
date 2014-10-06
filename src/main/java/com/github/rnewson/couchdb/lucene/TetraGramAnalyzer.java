package com.github.rnewson.couchdb.lucene;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.ngram.NGramTokenFilter;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;
import org.apache.lucene.util.Version;

public class TetraGramAnalyzer extends Analyzer {

	private Version version;

	public TetraGramAnalyzer(Version version) {
		this.version = version;
	}

    @Override
    protected TokenStreamComponents createComponents(String s, Reader reader) {
        // divide the query string on whitespaces
        Tokenizer source = new WhitespaceTokenizer(this.version, reader);
        // Tokenizer source = new NGramTokenizer(this.version, reader, 4, 4);
        // create tokenstream out of the words obtained from source above
        TokenStream result = new NGramTokenFilter(this.version, source, 4, 4);
        result = new LowerCaseFilter(this.version, result);
        return new TokenStreamComponents(source, result);
    }

}