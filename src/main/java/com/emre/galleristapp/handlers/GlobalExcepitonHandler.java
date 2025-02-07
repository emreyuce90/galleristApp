package com.emre.galleristapp.handlers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.lang.Exception;
import java.net.Inet4Address;
import java.util.*;

@ControllerAdvice
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GlobalExcepitonHandler {

    //uygulamada meydana gelen tüm hataları handle eder
    @ExceptionHandler(value={Exception.class})
    public ResponseEntity<ApiError<String>> handleException(Exception ex, WebRequest request){
        return ResponseEntity.badRequest().body(createApiError(ex.getMessage(), request));
    }

    private List<String> addMessages(List<String> messages, String item){
        messages.add(item);
        return messages;
    }

    //validasyon hatalarını handle et
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiError<Object>> handleMethodNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
       //validasyon hatalarını yakalamak için k,v şeklinde bir map oluştur
        Map<String, List<String>> errors = new HashMap<>();
        //oluşan hataları foreach ile dön
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            //hatanın fieldName ini değişkene ata
            String fieldName = ((FieldError)error).getField();
            //yukarıda oluşturduğumuz errors içerisinde bu fieldName in olup olmadığını kontrol et
            if(errors.containsKey(fieldName)) {
                //buraya girdiğine göre zaten fieldName mevcut o zaman yeni liste oluşturma mevcut listeye ekleme yap
                //mevcut listeyi çek üzerine ekle
                errors.put(fieldName,addMessages(errors.get(fieldName), fieldName));
            }else{
                //fieldName yok ise keyi fieldName olan - valuesu da List<String> içerisine gelen hata mesajı
                // eklenmiş olan veriyi ekle
                errors.put(fieldName,addMessages(new ArrayList<>(),error.getDefaultMessage()));
            }
        });
        //meydana gelen hataları özelleştirebilmek için K,V pair çifti şeklinde hazırlayalım
        return ResponseEntity.badRequest().body(createApiError(errors, request));

    }




    private String getHostName(){
       try{
          return Inet4Address.getLocalHost().getHostName();
       }catch (Exception e){
           e.printStackTrace();
       }
       return "";
    }

    public <E> ApiError<E> createApiError(E message,WebRequest request){
        ApiError<E> apiError = new ApiError<>();
        //status code değeri
        apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        CustomException<E> customException = new CustomException<>();
        customException.setMessage(message);
        customException.setPath(request.getDescription(false).substring(4));
        customException.setCreatedTime(new Date());
        customException.setHostName(getHostName());

        apiError.setException(customException);

        return apiError;

    }
}
