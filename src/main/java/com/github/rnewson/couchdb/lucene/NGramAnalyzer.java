package com.github.rnewson.couchdb.lucene;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.ngram.NGramTokenFilter;
import org.apache.lucene.analysis.ngram.NGramTokenizer;
import org.apache.lucene.util.Version;

public class NGramAnalyzer extends Analyzer {

	private Version version;

	public NGramAnalyzer(Version version) {
		this.version = version;
	}

    @Override
    protected TokenStreamComponents createComponents(String s, Reader reader) {
        Tokenizer source = new NGramTokenizer(this.version, reader, 2, 3);
        TokenStream result = new NGramTokenFilter(this.version, source);
        result = new LowerCaseFilter(this.version, result);
        return new TokenStreamComponents(source, result);
    }

}