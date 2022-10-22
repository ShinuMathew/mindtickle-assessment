echo "Dependency installation and project build begins..."
mvn clean install -DskipTests=true

if [ $? -eq 0 ]; then
    echo "Dependency installation and project build successfull!"
    sh ./scripts/install-hooks.sh
fi