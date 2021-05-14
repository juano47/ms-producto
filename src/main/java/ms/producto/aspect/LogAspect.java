package ms.producto.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(LogAspect. class);
	
	@Pointcut("execution(* ms.producto.service.MaterialService.*(..))")
	private void MaterialServiceMethods() {}
	
	@Pointcut("execution(public * org.springframework.data.jpa.repository.JpaRepository+.*(..))")
	private void repositoryMethods() {}
	
	@Before("repositoryMethods()")
	public void logBefore(JoinPoint jp) {
		logger.info("Se ejecutará: "+jp.getSignature().getName()+Arrays.toString(jp.getArgs()));
	}
	
//	@Before("MaterialServiceMethods()")
//	public void logBefore(JoinPoint jp) {
//		logger.info("Se ejecutará: "+jp.getSignature().getName());
//	}
}
