# myshows-rpc
![Tests](https://github.com/srgsf/myshows-rpc/workflows/Tests/badge.svg?branch=master&event=push)
![License](https://img.shields.io/github/license/srgsf/myshows-rpc)
![Maven](https://img.shields.io/maven-central/v/com.github.srgsf/myshows-rpc)

A Java wrapper around https://myshows.me [Json-Rpc API v2.0](https://api.myshows.me/shared/doc/) using [Retrofit 2](https://square.github.io/retrofit/) and [Moshi](https://github.com/square/moshi#readme) for json serialization.

Current version contains the following APIs:
* ~~auth~~
* ~~iap~~
* lists
* manage
* ~~news~~
* notes
* profile
* ~~push~~
* recommendation
* shows
* ~~site~~
* users

Pull requests are welcome.

## Usage

Add the following dependency to your Gradle project:

```groovy
implementation 'com.github.srgsf:myshows-rpc:0.1.0'
```

Or for Maven:

```xml
<dependency>
  <groupId>com.github.srgsf</groupId>
  <artifactId>myshows-rpc</artifactId>
  <version>0.1.0</version>
</dependency>
```

### Example

Use like any other retrofit2 based service.  
Optionally you can share OkHttp client and Retrofit instances to keep single request pooling, disk cache, routing logic, etc.

```java
 RpcClient client = new RpcClient.Builder().build();
        Shows shows = client.shows();
        Call<List<Show>> call = shows.search(new Query("House"));
        call.enqueue(new Callback<List<Show>>() {
            @Override
            public void onResponse(Call<List<Show>> call, Response<List<Show>> response) {
                if(response.isSuccessful()){
                    List<Show> result = response.body();
                    //use list of found shows.
                } else {
                    try {
                        RpcError error = client.error(response.errorBody());
                        //analyse RPC error.
                    }catch (IOException ex){
                       ex.printStackTrace(); 
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Show>> call, Throwable t) {

            }
        });
```
### Authorization

To use Rpc methods that require valid OAuth 2.0 access token you must either provide `RpcClient` with `AccessTokenProvider` implementation or add `okhttp3.Interceptor` that handles authorization.  
Please note that `okhttp3.Authenticator` is not used due to Json-Rpc always responses with 200 http status.  
Library includes `MyShowsTokenProvider` example implementation of `AccessTokenProvider` however it is **strongly recommended** using implementation that fits your key management strategy. 

```java
        //shared 
        OkHttpClient httpClient = new OkHttpClient.Builder().build();
        //shared
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create()).baseUrl("/").build();

        MyShowsAuthClient authClient = new MyShowsAuthClient.Builder()
                .client(httpClient)
                .retrofit(retrofit)
                .clientCredentials(new Credentials("api_user", "api_secret"))
                .build();
        try {
            //using password grant
            Response<AccessToken> response = authClient.accessToken(new Credentials("myshows_user",
                    "myshows_password"));
            if (!response.isSuccessful()) {
                throw new RuntimeException("authentication failed.");
            }

            RpcClient client = new RpcClient.Builder()
                    .client(httpClient)
                    .retrofit(retrofit)
                    .tokenProvider(new MyShowsTokenProvider(authClient, response.body()))
                    .build();
            Profile profile = client.profile();
            Response<UserProfile> profileResponse = profile.get(new UserLogin("my_shows_login")).execute();
            if (profileResponse.isSuccessful()) {
                UserProfile p = profileResponse.body();
                //use profile
            }
        } catch (IOException ex) {
            //handle
        }
```

See test cases in `src/test/` for more examples.
