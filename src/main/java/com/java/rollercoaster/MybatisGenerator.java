package com.java.rollercoaster;

import org.mybatis.generator.api.ShellRunner;

public final class MybatisGenerator {
    private MybatisGenerator() { }

    /**
     * Generator the mybatis related files.
     *
     * @param args args.
     */
    public static void main(final String[] args) {

        String config = MybatisGenerator.class.getClassLoader()
                .getResource("generatorConfig.xml").getFile();
        String[] arg = {"-configfile", config, "-overwrite"};
        ShellRunner.main(arg);
    }
}
