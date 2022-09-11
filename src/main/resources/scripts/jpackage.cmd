cd /c/users/Geo/OneDrive/Git/mrradb
jpackage \
--description 'Mercy Ridge Residents Association Database Management' \
--icon src/main/resources/mrradb.ico \
--input target/ \
--main-class net.stumpwiz.mrradb.DbApplication \
--main-jar mrradb-1.0-SNAPSHOT.jar \
--name mrradb \
--resource-dir src/main/resources/ \
--type MSI \
--vendor 'George Wright' \
--win-dir-chooser \
--win-shortcut \
--win-per-user-install \
--win-menu