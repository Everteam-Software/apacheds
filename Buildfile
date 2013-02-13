require "buildr"

repositories.remote = [ 
  "http://www.intalio.org/public/maven2", 
  "http://repo1.maven.org/maven2",
  "http://people.apache.org/repo/m2-ibiblio-rsync-repository/"
]

repositories.release_to[:username] ||= "release"
repositories.release_to[:url] ||= "sftp://www.intalio.org/var/www-org/public/maven2"
repositories.release_to[:permissions] ||= 0664

# Keep this structure to allow the build system to update version numbers.
VERSION_NUMBER = "6.0.0.41-SNAPSHOT"
DP_VERSION_NUMBER="1.0.1"

if ENV['DP_VERSION_NUMBER']
DP_VERSION_NUMBER = "#{ENV['DP_VERSION_NUMBER']}"
end

# We need to download the artifact before we load the same
artifact("org.intalio.common:dependencies:rb:#{DP_VERSION_NUMBER}").invoke

DEPENDENCIES = "#{ENV['HOME']}/.m2/repository/org/intalio/common/dependencies/#{DP_VERSION_NUMBER}/dependencies-#{DP_VERSION_NUMBER}.rb"
if ENV["M2_REPO"]
DEPENDENCIES ="#{ENV['M2_REPO']}/org/intalio/common/dependencies/#{DP_VERSION_NUMBER}/dependencies-#{DP_VERSION_NUMBER}.rb"
end
puts "Loading #{DEPENDENCIES}"
load DEPENDENCIES


desc "Embedded Apache Directory Service"
define "apacheds-webapp" do
  project.version = VERSION_NUMBER
  project.group = "org.intalio.tempo"
  compile.options.target = "1.5"
  
  libs = [APACHE_DS_DEPS, APACHE_COMMONS[:lang], APACHE_COMMONS[:collections], JSON_NAGGIT, SLF4J.values]
  compile.with(libs, SERVLET_API)
  test.with(libs, LOG4J)
  package(:war).with :libs => libs
end
