require "buildr"

# Keep this structure to allow the build system to update version numbers.
VERSION_NUMBER = "6.0.0.36"

repositories.remote = [ 
  "http://www.intalio.org/public/maven2", 
  "http://repo1.maven.org/maven2",
  "http://people.apache.org/repo/m2-ibiblio-rsync-repository/"
]

repositories.release_to[:username] ||= "release"
repositories.release_to[:url] ||= "sftp://www.intalio.org/var/www-org/public/maven2"
repositories.release_to[:permissions] ||= 0664

APACHE_COMMONS = {
  :collections => "commons-collections:commons-collections:jar:3.2", 
  :lang => "commons-lang:commons-lang:jar:2.3",
}
APACHE_DS = "org.apache.apacheds:apacheds-deps:jar:1.5.4"
JSON_NAGGIT = "org.apache:naggit:jar:1.0.20080807"
SLF4J = group(%w{ slf4j-api slf4j-log4j12 jcl104-over-slf4j }, :under=>"org.slf4j", :version=>"1.4.3")
LOG4J = "log4j:log4j:jar:1.2.15"
SERVLET_API = "javax.servlet:servlet-api:jar:2.4" 

desc "Embedded Apache Directory Service"
define "apacheds-webapp" do
  project.version = VERSION_NUMBER
  project.group = "org.intalio.tempo"
  compile.options.target = "1.5"
  
  libs = [APACHE_DS, APACHE_COMMONS[:lang], APACHE_COMMONS[:collections], JSON_NAGGIT, SLF4J]
  compile.with(libs, SERVLET_API)
  test.with(libs, LOG4J)
  package(:war).with :libs => libs
end