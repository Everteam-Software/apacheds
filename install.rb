DP_VERSION_NUMBER="1.0.1"

if ENV['DP_VERSION_NUMBER']
DP_VERSION_NUMBER = "#{ENV['DP_VERSION_NUMBER']}"
end

repositories.remote << "http://release.intalio.com/m2repo/"
repositories.remote << "http://www.intalio.org/public/maven2"
repositories.remote << "http://people.apache.org/repo/m2-incubating-repository"
repositories.remote << "http://repo1.maven.org/maven2"
repositories.remote << "http://people.apache.org/repo/m2-snapshot-repository"
repositories.remote << "http://download.java.net/maven/2"
repositories.remote << "http://ws.zones.apache.org/repository2"
repositories.remote << "http://mirrors.ibiblio.org/pub/mirrors/maven2"
repositories.remote << "http://svn.apache.org/repos/asf/servicemix/m2-repo"
repositories.release_to[:url] ||= "sftp://release@release.intalio.com/home/release/m2repo"
repositories.release_to[:password] ||= "release"

# We need to download the artifact before we load the same
artifact("org.intalio.common:dependencies:zip:#{DP_VERSION_NUMBER}").invoke

DEPENDENCIES = "#{ENV['HOME']}/.m2/repository/org/intalio/common/dependencies/#{DP_VERSION_NUMBER}/dependencies-#{DP_VERSION_NUMBER}.zip"
if ENV["M2_REPO"]
  DEPENDENCIES = "#{ENV['M2_REPO']}/org/intalio/common/dependencies/#{DP_VERSION_NUMBER}/dependencies-#{DP_VERSION_NUMBER}.zip"
end

rm_rf "build"
mkdir "build"
cp "#{DEPENDENCIES}", "build"
unzip = Buildr::Unzip.new("build"=>"#{DEPENDENCIES}")
unzip.extract
