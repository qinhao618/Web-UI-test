echo start web ui automation testing
export browser='chrome'
echo now the browser is $browser
export env='test'
echo now the environment is $env

gradle cucumber

echo finish testing

